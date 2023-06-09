package com.miracle.AMAG.controller.account;

import com.miracle.AMAG.service.account.UserMypageService;
import com.miracle.AMAG.util.board.BoardUtils;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "UserMypage", description = "사용자 마이페이지 관련 API")
@RequestMapping("/api/user/mypage")
@RestController
@RequiredArgsConstructor
public class UserMypageController {

    @Autowired
    private UserMypageService userMypageService;

    @GetMapping("/share-articles/{page}/{size}/{type}")
    @Operation(summary = "사용자 공유 물품 관련 내역 목록 조회", description = "사용자가 공유하거나 사용한 물품 내역 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 공유 물품 관련 내역 조회 성공", content = @Content(schema = @Schema(implementation = NormalResponse.class))),
            @ApiResponse(responseCode = "500", description = "사용자 공유 물품 관련 내역 조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "요청이 잘못되었습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호",in = ParameterIn.PATH),
            @Parameter(name = "size", description = "페이지 당 내역 개수",in = ParameterIn.PATH),
            @Parameter(name = "type", description = "조회 타입(0: 회수 , 1: 보관, 2: 사용, 3: 반납)",in = ParameterIn.PATH)
    })
    public ResponseEntity<?> getUseList(@PathVariable("page") int page, @PathVariable("size") int size,
                                        @PathVariable("type") int type) {
        PageRequest pageRequest = BoardUtils.pageRequestInit(page,size, "id" , BoardUtils.ORDER_BY_DESC);
        return NormalResponse.toResponseEntity(HttpStatus.OK, userMypageService.getUseList(type, pageRequest));
    }

    @GetMapping("/like/{page}/{size}")
    @Operation(summary = "사용자가 찜한 목록 조회", description = "사용자가 찜한 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자가 찜한 목록 조회 성공", content = @Content(schema = @Schema(implementation = NormalResponse.class))),
            @ApiResponse(responseCode = "500", description = "사용자가 찜한 목록 조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "요청이 잘못되었습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호",in = ParameterIn.PATH),
            @Parameter(name = "size", description = "페이지 당 찜한 목록 개수",in = ParameterIn.PATH)
    })
    public ResponseEntity<?> getLikeList(@PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        return NormalResponse.toResponseEntity(HttpStatus.OK, userMypageService.getLikeList(pageRequest));
    }

    @GetMapping("/posts/{page}/{size}")
    @Operation(summary = "사용자가 작성한 글 목록 조회", description = "사용자가 작성한 글 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자가 작성한 글 목록 조회 성공", content = @Content(schema = @Schema(implementation = NormalResponse.class))),
            @ApiResponse(responseCode = "500", description = "사용자가 작성한 글 목록 조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "요청이 잘못되었습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호",in = ParameterIn.PATH),
            @Parameter(name = "size", description = "페이지 당 작성한 글 개수",in = ParameterIn.PATH)
    })
    public ResponseEntity<?> getShareArticle(@PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        return NormalResponse.toResponseEntity(HttpStatus.OK, userMypageService.getShareArticle(pageRequest));
    }

    @GetMapping("/profits/{year}")
    @Operation(summary = "사용자가 얻은 수익 통계 조회", description = "사용자가 얻은 수익(현재는 결제 내역을 기준으로) 통계를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자가 얻은 수익(현재는 결제 내역을 기준으로) 통계 조회 성공", content = @Content(schema = @Schema(implementation = NormalResponse.class))),
            @ApiResponse(responseCode = "500", description = "사용자가 얻은 수익(현재는 결제 내역을 기준으로) 통계 조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "요청이 잘못되었습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @Parameters({
            @Parameter(name = "year", description = "수익 년도",in = ParameterIn.PATH)
    })
    public ResponseEntity<?> getProfits(@PathVariable("year") String year) {
        return NormalResponse.toResponseEntity(HttpStatus.OK, userMypageService.getPayment(year));
    }
}
