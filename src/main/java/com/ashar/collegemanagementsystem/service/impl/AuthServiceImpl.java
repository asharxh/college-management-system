package com.ashar.collegemanagementsystem.service.impl;

import com.ashar.collegemanagementsystem.dto.ChangePasswordDTO;
import com.ashar.collegemanagementsystem.dto.ForgotPasswordDTO;
import com.ashar.collegemanagementsystem.dto.ResetPasswordDTO;
import com.ashar.collegemanagementsystem.dto.UpdateStudentDTO;
import com.ashar.collegemanagementsystem.dto.request.AdminRegisterDTO;
import com.ashar.collegemanagementsystem.dto.request.FacultyRegisterDTO;
import com.ashar.collegemanagementsystem.dto.request.LoginDTO;
import com.ashar.collegemanagementsystem.dto.request.StudentRegisterDTO;
import com.ashar.collegemanagementsystem.dto.response.ApiResponse;
import com.ashar.collegemanagementsystem.entity.Admin;
import com.ashar.collegemanagementsystem.entity.FacultyPersonal;
import com.ashar.collegemanagementsystem.entity.PasswordResetToken;
import com.ashar.collegemanagementsystem.entity.Student;
import com.ashar.collegemanagementsystem.repository.AdminRepository;
import com.ashar.collegemanagementsystem.repository.FacultyRepository;
import com.ashar.collegemanagementsystem.repository.PasswordResetTokenRepository;
import com.ashar.collegemanagementsystem.repository.StudentRepository;
import com.ashar.collegemanagementsystem.security.JwtUtil;
import com.ashar.collegemanagementsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final AdminRepository adminRepository;
    private final JwtUtil jwtUtil;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ApiResponse registerStudent(StudentRegisterDTO request) {

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .branch(request.getBranch())
                .enrollmentYear(request.getEnrollmentYear())
                .createdAt(LocalDateTime.now())
                .build();

        studentRepository.save(student);

        return ApiResponse.builder()
                .success(true)
                .message("Student registered successfully")
                .data(student.getId())
                .build();
    }

    @Override
    public ApiResponse registerFaculty(FacultyRegisterDTO request) {

        if (facultyRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        FacultyPersonal faculty = FacultyPersonal.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .department(request.getDepartment())
                .designation(request.getDesignation())
                .createdAt(LocalDateTime.now())
                .build();

        facultyRepository.save(faculty);

        return ApiResponse.builder()
                .success(true)
                .message("Faculty registered successfully")
                .data(faculty.getId())
                .build();
    }

    @Override
    public ApiResponse registerAdmin(AdminRegisterDTO request) {

        if (adminRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Admin admin = Admin.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .createdAt(LocalDateTime.now())
                .build();

        adminRepository.save(admin);

        return ApiResponse.builder()
                .success(true)
                .message("Admin registered successfully")
                .data(admin.getId())
                .build();
    }

    @Override
    public ApiResponse loginStudent(LoginDTO request) {

        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), student.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(student.getId(), student.getEmail(), "STUDENT");

        Map<String, Object> data = Map.of(
                "token", token,
                "type", "Bearer",
                "userId", student.getId(),
                "name", student.getName(),
                "email", student.getEmail(),
                "role", "STUDENT"
        );

        return ApiResponse.builder()
                .success(true)
                .message("Login successful")
                .data(data)
                .build();
    }

    @Override
    public ApiResponse loginFaculty(LoginDTO request) {

        FacultyPersonal faculty = facultyRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), faculty.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(
                faculty.getId(),
                faculty.getEmail(),
                "FACULTY"
        );
        Map<String, Object> data = Map.of(
                "token", token,
                "type", "Bearer",
                "userId", faculty.getId(),
                "name", faculty.getName(),
                "email", faculty.getEmail(),
                "role", "FACULTY"
        );

        return ApiResponse.builder()
                .success(true)
                .message("Login successful")
                .data(data)
                .build();
    }

    @Override
    public ApiResponse loginAdmin(LoginDTO request) {

        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(
                admin.getId(),
                admin.getEmail(),
                admin.getRole()
        );
        Map<String, Object> data = Map.of(
                "token", token,
                "type", "Bearer",
                "userId", admin.getId(),
                "name", admin.getName(),
                "email", admin.getEmail(),
                "role", admin.getRole()
        );

        return ApiResponse.builder()
                .success(true)
                .message("Login successful")
                .data(data)
                .build();
    }

    @Override
    public ApiResponse forgotPassword(ForgotPasswordDTO request) {

        String email = request.getEmail();

        boolean exists = studentRepository.existsByEmail(email)
                || facultyRepository.existsByEmail(email)
                || adminRepository.existsByEmail(email);

        if (!exists) {
            throw new RuntimeException("Email not registered");
        }

        passwordResetTokenRepository.deleteByEmail(email);

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = PasswordResetToken.builder()
                .email(email)
                .token(token)
                .expiryDate(LocalDateTime.now().plusMinutes(2))
                .build();

        passwordResetTokenRepository.save(resetToken);

        System.out.println("RESET TOKEN: " + token);

        return ApiResponse.builder()
                .success(true)
                .message("Reset link sent to email")
                .build();
    }

    @Override
    public ApiResponse resetPassword(ResetPasswordDTO request) {

        PasswordResetToken resetToken = passwordResetTokenRepository
                .findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        String email = resetToken.getEmail();

        if (studentRepository.existsByEmail(email)) {

            Student student = studentRepository.findByEmail(email).get();
            student.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
            studentRepository.save(student);

        } else if (facultyRepository.existsByEmail(email)) {

            FacultyPersonal faculty = facultyRepository.findByEmail(email).get();
            faculty.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
            facultyRepository.save(faculty);

        } else if (adminRepository.existsByEmail(email)) {

            Admin admin = adminRepository.findByEmail(email).get();
            admin.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
            adminRepository.save(admin);
        }

        passwordResetTokenRepository.delete(resetToken);

        return ApiResponse.builder()
                .success(true)
                .message("Password reset successful")
                .build();
    }

    @Override
    public ApiResponse changePassword(ChangePasswordDTO request) {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (studentRepository.existsByEmail(email)) {

            Student student = studentRepository.findByEmail(email).get();

            if (!passwordEncoder.matches(request.getOldPassword(), student.getPasswordHash())) {
                throw new RuntimeException("Old password is incorrect");
            }

            student.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
            studentRepository.save(student);

        } else if (facultyRepository.existsByEmail(email)) {

            FacultyPersonal faculty = facultyRepository.findByEmail(email).get();

            if (!passwordEncoder.matches(request.getOldPassword(), faculty.getPasswordHash())) {
                throw new RuntimeException("Old password is incorrect");
            }

            faculty.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
            facultyRepository.save(faculty);

        } else if (adminRepository.existsByEmail(email)) {

            Admin admin = adminRepository.findByEmail(email).get();

            if (!passwordEncoder.matches(request.getOldPassword(), admin.getPasswordHash())) {
                throw new RuntimeException("Old password is incorrect");
            }

            admin.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
            adminRepository.save(admin);
        }

        return ApiResponse.builder()
                .success(true)
                .message("Password changed successfully")
                .build();
    }

    @Override
    public ApiResponse getStudentProfile() {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Map<String, Object> data = new HashMap<>();

        data.put("id", student.getId());
        data.put("name", student.getName());
        data.put("email", student.getEmail());
        data.put("phone", student.getPhone());
        data.put("branch", student.getBranch());
        data.put("semester", student.getSemester());
        data.put("enrollmentYear", student.getEnrollmentYear());
        data.put("address", student.getAddress());
        data.put("city", student.getCity());
        data.put("pincode", student.getPincode());

        return ApiResponse.builder()
                .success(true)
                .message("Profile fetched successfully")
                .data(data)
                .build();
    }

    @Override
    public ApiResponse updateStudentProfile(UpdateStudentDTO request) {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (request.getPhone() != null) {
            student.setPhone(request.getPhone());
        }

        if (request.getAddress() != null) {
            student.setAddress(request.getAddress());
        }

        if (request.getCity() != null) {
            student.setCity(request.getCity());
        }

        if (request.getPincode() != null) {
            student.setPincode(request.getPincode());
        }

        studentRepository.save(student);

        return ApiResponse.builder()
                .success(true)
                .message("Profile updated successfully")
                .build();
    }
}