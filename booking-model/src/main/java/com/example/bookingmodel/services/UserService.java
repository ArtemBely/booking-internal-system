package com.example.bookingmodel.services;

import com.example.bookingmodel.data.dto.ApartmentsoverviewDto;
import com.example.bookingmodel.data.dto.BinaryContentDto;
import com.example.bookingmodel.data.dto.CustomerDto;
import com.example.bookingmodel.data.entity.Apartmentsoverview;
import com.example.bookingmodel.data.entity.BinaryContent;
import com.example.bookingmodel.data.entity.Customer;
import com.example.bookingmodel.data.entity.Role;
import com.example.bookingmodel.data.mapper.ApartmentsOverviewMapper;
import com.example.bookingmodel.data.mapper.CustomerMapper;
import com.example.bookingmodel.interfaces.IUserService;
import com.example.bookingmodel.repositories.BinaryContentRepository;
import com.example.bookingmodel.repositories.CustomerRepository;
import com.example.bookingmodel.utilities.AuthUtils;
import com.example.bookingmodel.utilities.DefaultConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements IUserService {

    private final CustomerRepository customerRepository;

    private final BinaryContentRepository binaryContentRepository;

    private final CustomerMapper customerMapper;

    private final ApartmentsOverviewMapper apartmentsOverviewMapper;

    private final JdbcTemplate jdbcTemplate;

    private String getNextLevel, getBirthdayData, getApartmentsOverview, getBinaryContentByUserId;

    @Autowired
    public UserService(CustomerRepository customerRepository,
                       BinaryContentRepository binaryContentRepository,
                       CustomerMapper customerMapper,
                       ApartmentsOverviewMapper apartmentsOverviewMapper,
                       JdbcTemplate jdbcTemplate,
                       @Value("${sql.getNextLevel}") String getNextLevel,
                       @Value("${sql.getBirthdayData}") String getBirthdayData,
                       @Value("${sql.getApartmentsOverview}") String getApartmentsOverview,
                       @Value("${sql.getBinaryContentByUserId}") String getBinaryContentByUserId) {
        this.customerRepository = customerRepository;
        this.binaryContentRepository = binaryContentRepository;
        this.customerMapper = customerMapper;
        this.apartmentsOverviewMapper = apartmentsOverviewMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.getNextLevel = getNextLevel;
        this.getBirthdayData = getBirthdayData;
        this.getApartmentsOverview = getApartmentsOverview;
        this.getBinaryContentByUserId = getBinaryContentByUserId;
    }


    @Override
    public String getNextLevel() {
        return jdbcTemplate.queryForObject(getNextLevel, new Object[]{AuthUtils.getActualLevelId()}, String.class);
    }

    @Override
    public String getBirthdayData() {
        Object[] params = new Object[]{AuthUtils.getActualBirthday(), DefaultConstants.DATE_FORMAT};
        return jdbcTemplate.queryForObject(getBirthdayData, params, String.class);
    }

    @Override
    public List<ApartmentsoverviewDto> getGlobalApartment() {
        List<Apartmentsoverview> overview = jdbcTemplate.query(getApartmentsOverview, new BeanPropertyRowMapper<>(Apartmentsoverview.class));
        return overview.stream()
                .map(apartmentsOverviewMapper::mapToDto)
                .collect(Collectors.toList());
    }

    //    @Override
//    @Transactional
//    public void saveUserPhoto(int userId, byte[] photoBytes, String fileName, String fileExtension) {
//        Customer user = customerRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User doesn't exists"));
//        BinaryContent binaryContent = new BinaryContent();
//        binaryContent.setContent(photoBytes);
//        binaryContent.setFileName(fileName);
//        binaryContent.setFileExtension(fileExtension);
//        binaryContent.setUploadDate(LocalDate.now());
//        binaryContent.setOperationPerformedBy(Integer.toString(userId));
//        BinaryContent savedBinaryContent = binaryContentRepository.save(binaryContent);
//        user.setContentId(savedBinaryContent.getId());
//        customerRepository.save(user);
//    }
    @Override
    @Transactional
    public void saveUserPhoto(int userId, byte[] photoBytes, String fileName, String fileExtension) {
        Customer user = customerRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User doesn't exist"));

        BinaryContent binaryContent;
        if (user.getContentId() != null) {
            binaryContent = binaryContentRepository.findById(user.getContentId())
                    .orElseThrow(() -> new RuntimeException("BinaryContent doesn't exist"));
            binaryContent.setContent(photoBytes);
            binaryContent.setFileName(fileName);
            binaryContent.setFileExtension(fileExtension);
            binaryContent.setModificationDate(LocalDate.now());
            binaryContent.setOperationPerformedBy(Integer.toString(userId));
        } else {
            binaryContent = new BinaryContent();
            binaryContent.setContent(photoBytes);
            binaryContent.setFileName(fileName);
            binaryContent.setFileExtension(fileExtension);
            binaryContent.setUploadDate(LocalDate.now());
            binaryContent.setOperationPerformedBy(Integer.toString(userId));
        }
        BinaryContent savedBinaryContent = binaryContentRepository.save(binaryContent);
        user.setContentId(savedBinaryContent.getId());
        customerRepository.save(user);
    }


    @Override
    public BinaryContentDto getFileDataByUser(int userId) {
        return jdbcTemplate.queryForObject(
                getBinaryContentByUserId,
                new Object[]{userId},
                (rs, rowNum) -> {
                    BinaryContentDto dto = new BinaryContentDto();
                    dto.setId(rs.getLong("id"));
                    dto.setFileName(rs.getString("file_name"));
                    dto.setFileExtension(rs.getString("file_extension"));
                    dto.setContent(rs.getBytes("content"));
                    dto.setUploadDate(rs.getObject("upload_date", LocalDate.class));
                    dto.setModificationDate(rs.getObject("modification_date", LocalDate.class));
                    dto.setOperationPerformedBy(rs.getString("operation_performed_by"));
                    return dto;
                });
    }

    @Override
    public String updateUser(CustomerDto userDto) {
        try {
            jdbcTemplate.update(
                    connection -> {
                        CallableStatement cs = connection.prepareCall(
                                "{CALL EditUserById(?, ?, ?, ?, ?, ?, ?)}"
                        );
                        cs.setInt(1, userDto.getId());
                        cs.setString(2, userDto.getName());
                        cs.setString(3, userDto.getSurname());
                        cs.setString(4, userDto.getPhone());
                        cs.setDate(5, new java.sql.Date(userDto.getDateOfBirth().getTime()));
                        cs.setString(6, userDto.getEmail());
                        cs.setLong(7, userDto.getLevelId());
                        return cs;
                    }
            );
        } catch (Exception ex) {
            log.error("Error updating user: {}", ex.getMessage());
            return ex.getMessage();
        }
        return "Success";
    }

}