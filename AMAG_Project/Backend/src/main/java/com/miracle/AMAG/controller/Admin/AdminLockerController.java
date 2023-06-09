package com.miracle.AMAG.controller.Admin;

import com.miracle.AMAG.service.admin.AdminLockerService;
import com.miracle.AMAG.service.common.AddressService;
import com.miracle.AMAG.util.network.CUDResponse;
import com.miracle.AMAG.util.network.ErrorResponse;
import com.miracle.AMAG.util.network.NormalResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Admin_LockerControll", description = "사물함 정보 및 컨트롤")
@RequestMapping("/api/admin/lockers")
@RequiredArgsConstructor
@RestController
public class AdminLockerController {

    @Autowired
    AdminLockerService adminLockerService;

    @Autowired
    AddressService addressService;

    @GetMapping("/address/sido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "시도 전체 목록 조회 성공", content = @Content(schema = @Schema(implementation = NormalResponse.class))),
            @ApiResponse(responseCode = "500", description = "시도 전체 목록 조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "요청이 잘못되었습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "시도 조회",description = "시도 전체 목록을 조회합니다.")
    public ResponseEntity<?> getSidoList(){
        return NormalResponse.toResponseEntity(HttpStatus.OK,addressService.getSido());
    }

    @GetMapping("/address/sido/{sido}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "지역내 lockerStation 목록 조회 성공", content = @Content(schema = @Schema(implementation = NormalResponse.class))),
            @ApiResponse(responseCode = "500", description = "지역내 lockerStation 목록 조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "요청이 잘못되었습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "지역내 사물함 목록 조회",description = "지역내 사물함 목록을 조회합니다.")
    @Parameters({
            @Parameter(name = "sido", description = "시도",in = ParameterIn.PATH)
    })
    public ResponseEntity<?> getLockerStationList(@PathVariable("sido") String sido){
        return NormalResponse.toResponseEntity(HttpStatus.OK,adminLockerService.getLockerStationList(sido));
    }


    @GetMapping("/{lockerStationId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "지점별 사물함 번호 목록 조회 성공", content = @Content(schema = @Schema(implementation = NormalResponse.class))),
            @ApiResponse(responseCode = "500", description = "지점별 사물함 번호 목록 조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "요청이 잘못되었습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "지점별 사물함 번호 목록 조회", description = "지점별 사물함 번호 목록 조회합니다")
    @Parameters({
            @Parameter(name = "lockerStationId", description = "지점id",in = ParameterIn.PATH),
            @Parameter(name = "page", description = "글 페이지 번호",in = ParameterIn.PATH),
            @Parameter(name = "size", description = "페이지 당 글 수",in = ParameterIn.PATH)
    })
    public ResponseEntity<?> getLockerList(@PathVariable("lockerStationId") int lockerStationId){
        return NormalResponse.toResponseEntity(HttpStatus.OK,adminLockerService.getLockerList(lockerStationId));
    }

    @PostMapping("/collect/{lockerId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "관리자 물품 회수 신청 성공", content = @Content(schema = @Schema(implementation = CUDResponse.class))),
            @ApiResponse(responseCode = "500", description = "관리자 물품 회수 신청 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "요청이 잘못되었습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "관리자 물품 회수 신청", description = "공유했던 물품을 회수 신청하는 기능입니다.")
    @Parameters({
            @Parameter(name = "lockerId", description = "회수 신청을 진행할 대여함 ID", in = ParameterIn.PATH),
    })
    public ResponseEntity<?> adminApplyCollect(@PathVariable("lockerId") int lockerId) throws IOException {
        return NormalResponse.toResponseEntity(HttpStatus.OK, adminLockerService.adminApplyCollect(lockerId));
    }

    @PostMapping("/collect/cancel/{lockerId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "관리자 물품 회수 신청취소 성공", content = @Content(schema = @Schema(implementation = CUDResponse.class))),
            @ApiResponse(responseCode = "500", description = "관리자 물품 회수 신청취소 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "요청이 잘못되었습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "관리자 물품 회수 신청취소", description = "관리자가 회수 신청 했던 공유 물품에 대해서 신청취소를 진행합니다.")
    @Parameters({
            @Parameter(name = "lockerId", description = "대여 물품 게시글 ID", in = ParameterIn.PATH),
    })
    public ResponseEntity<?> adminCancelKeep(@PathVariable("lockerId") int lockerId) throws IOException {
        return NormalResponse.toResponseEntity(HttpStatus.OK, adminLockerService.adminCancelCollect(lockerId));
    }

    @PostMapping("/collect/receive/{lockerId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "관리자 공유 물품 회수 성공", content = @Content(schema = @Schema(implementation = CUDResponse.class))),
            @ApiResponse(responseCode = "500", description = "관리자 공유 물품 회수 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "요청이 잘못되었습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "관리자 공유 물품 회수", description = "관리자가 공유 물품 회수를 진행합니다.")
    @Parameters({
            @Parameter(name = "lockerId", description = "회수를 진행할 대여함 ID", in = ParameterIn.PATH)
    })
    public ResponseEntity<?> adminCollectProduct(@PathVariable("lockerId") int lockerId) throws IOException {
        return NormalResponse.toResponseEntity(HttpStatus.OK, adminLockerService.adminCollectProduct(lockerId));
    }

    @PostMapping("/collect/nfc/open/{nfc_data}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "대여함 오픈 성공", content = @Content(schema = @Schema(implementation = CUDResponse.class))),
            @ApiResponse(responseCode = "500", description = "대여함 오픈 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "요청이 잘못되었습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Operation(summary = "관리자가 물품을 회수하기 위한 대여함 오픈", description = "관리자가 공유 물품을 회수하기 위해 대여함을 오픈하는 API")
    @Parameters({
            @Parameter(name = "nfc_data", description = "NFC 시리얼 번호", in = ParameterIn.PATH),
    })
    public ResponseEntity<?> adminOpenLockerForCollect(@PathVariable("nfc_data") String nfcData) throws IOException {
        return NormalResponse.toResponseEntity(HttpStatus.OK, adminLockerService.openLocker(nfcData));
    }

}

