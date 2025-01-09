//package istad.codeadvisor.userservice.feature.fileupload;
//
//import istad.codeadvisor.userservice.feature.fileupload.dto.ProfileImageResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping
//public class FileUploadController {
//    private final FileUploadService fileUploadService;
//
//    @PostMapping("/upload")
//    ProfileImageResponse uploadProfileImage(@RequestParam("file") MultipartFile file) {
//        return fileUploadService.uploadProfileImage(file);
//    }
//
//}
