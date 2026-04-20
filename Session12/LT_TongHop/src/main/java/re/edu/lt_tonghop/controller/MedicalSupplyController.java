package re.edu.lt_tonghop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.lt_tonghop.dto.request.InventoryRequestDTO;
import re.edu.lt_tonghop.dto.request.MedicalSupplyCreateDTO;
import re.edu.lt_tonghop.dto.request.MedicalSupplyUpdateDTO;
import re.edu.lt_tonghop.dto.response.ApiResponse;
import re.edu.lt_tonghop.dto.response.DailyExportResponse;
import re.edu.lt_tonghop.dto.response.MedicalSupplyResponse;
import re.edu.lt_tonghop.dto.response.TopExportResponse;
import re.edu.lt_tonghop.entity.MedicalSupply;
import re.edu.lt_tonghop.service.IMedicalSupplyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/supplies")
public class MedicalSupplyController {
    private final IMedicalSupplyService medicalSupplyService;

    @PostMapping
    public ResponseEntity<?> createMedicalSupply(
            @Valid @RequestBody MedicalSupplyCreateDTO dto
    ){
        MedicalSupplyResponse response = medicalSupplyService.createMedicalSupply(dto);
        ApiResponse<MedicalSupplyResponse> apiResponse = ApiResponse.<MedicalSupplyResponse>builder()
                .data(response)
                .message("Tạo mới vật tư thành công !")
                .status(HttpStatus.CREATED.value())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{supplyId}")
    public ResponseEntity<?> updateMedicalSupply(
            @PathVariable("supplyId") Long supplyId,
            @Valid @RequestBody MedicalSupplyUpdateDTO dto
    ){
        MedicalSupplyResponse response = medicalSupplyService.updateMedicalSupply(supplyId, dto);
        ApiResponse<MedicalSupplyResponse> apiResponse = ApiResponse.<MedicalSupplyResponse>builder()
                .data(response)
                .message("Cập nhật vật tư thành công !")
                .status(HttpStatus.OK.value())
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @DeleteMapping("/{supplyId}")
    public ResponseEntity<?> deleteMedicalSupply(
            @PathVariable("supplyId") Long supplyId
    ){
        medicalSupplyService.deleteMedicalSupply(supplyId);
        ApiResponse<MedicalSupplyResponse> apiResponse = ApiResponse.<MedicalSupplyResponse>builder()
                .message("Xóa vật tư thành công !")
                .status(HttpStatus.NO_CONTENT.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllMedicalSupply(){
        List<MedicalSupplyResponse> response = medicalSupplyService.getAllMedicalSupply();
        ApiResponse<List<MedicalSupplyResponse>> apiResponse = ApiResponse.<List<MedicalSupplyResponse>>builder()
                .data(response)
                .message("Lấy danh sách vật tư thành công !")
                .status(HttpStatus.OK.value())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchMedicalSupply(
            @RequestParam("name") String name
    ) {
        List<MedicalSupplyResponse> response = medicalSupplyService.getAllMedicalSupplyByName(name);
        ApiResponse<List<MedicalSupplyResponse>> apiResponse = ApiResponse.<List<MedicalSupplyResponse>>builder()
                .data(response)
                .message("Tìm kiếm danh sách vật tư thành công !")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/statistics/daily-export")
    public ResponseEntity<?> getDailyExport(
    ) {
        List<DailyExportResponse> response = medicalSupplyService.getDailyExport();
        ApiResponse<List<DailyExportResponse>> apiResponse = ApiResponse.<List<DailyExportResponse>>builder()
                .data(response)
                .message("Thống kê danh sách xuất vật tư trong ngày thành công !")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/statistics/top-export")
    public ResponseEntity<?> getTopExport(
    ) {
        TopExportResponse response = medicalSupplyService.getTopExport();
        ApiResponse<TopExportResponse> apiResponse = ApiResponse.<TopExportResponse>builder()
                .data(response)
                .message("Thống kê vật tư xuất nhiều nhất thành công !")
                .status(HttpStatus.OK.value())
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @PatchMapping("/{supplyId}/export")
    public ResponseEntity<?> exportMedicalSupply(
            @PathVariable Long supplyId,
            @Valid  @RequestBody InventoryRequestDTO dto
            ){
        MedicalSupplyResponse response = medicalSupplyService.exportSupply(supplyId, dto);
        ApiResponse<MedicalSupplyResponse> apiResponse = ApiResponse.<MedicalSupplyResponse>builder()
                .data(response)
                .message("Xuất vật tư thành công !")
                .status(HttpStatus.OK.value())
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @PatchMapping("/{supplyId}/import")
    public ResponseEntity<?> importMedicalSupply(
            @PathVariable Long supplyId,
            @Valid  @RequestBody InventoryRequestDTO dto
            ){
        MedicalSupplyResponse response = medicalSupplyService.importSupply(supplyId, dto);
        ApiResponse<MedicalSupplyResponse> apiResponse = ApiResponse.<MedicalSupplyResponse>builder()
                .data(response)
                .message("Nhập vật tư thành công !")
                .status(HttpStatus.OK.value())
                .build();

        return ResponseEntity.ok(apiResponse);
    }


}
