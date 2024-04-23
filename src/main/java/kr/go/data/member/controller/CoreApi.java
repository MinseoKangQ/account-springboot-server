package kr.go.data.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.go.data.member.dto.ChangePasswordDto;
import kr.go.data.member.dto.CheckPasswordDto;
import kr.go.data.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "로그인 이후에 사용하는 API", description = "header에 유효한 token값이 필수")
public interface CoreApi {

    @Operation(summary = "[회원정보 변경 페이지] 필요한 정보 전달")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 정보가 정상적으로 조회되었습니다.",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                                              "    \"status\": 200,\n" +
                                                              "    \"data\": {\n" +
                                                              "        \"email\": \"hihi@naver.com\",\n" +
                                                              "        \"phone\": \"01033221111\",\n" +
                                                              "        \"userId\": \"hihi\"\n" +
                                                              "    },\n" +
                                                              "    \"message\": \"회원 정보가 정상적으로 조회되었습니다.\"\n" +
                                                              "}"),
                            schema = @Schema(implementation = CustomApiResponse.class))),
            @ApiResponse(responseCode = "404", description = "해당 사용자를 찾을 수 없습니다.",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                                              "    \"status\": 404,\n" +
                                                              "    \"data\": null,\n" +
                                                              "    \"message\": \"id가 hihiss인 회원은 존재하지 않습니다.\"\n" +
                                                              "}"),
                            schema = @Schema(implementation = CustomApiResponse.class)))
    })
    @GetMapping("default-information")
    ResponseEntity<CustomApiResponse<?>> defaultInformation(
            @Parameter(name = "userId", description = "사용자의 아이디", in = ParameterIn.QUERY, required = true, example = "hello")
            @RequestParam("userId") String userId);

    @Operation(summary = "[회원정보 변경 페이지] 이전 비밀번호와 동일한지 확인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용 가능한 비밀번호입니다.",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                                              "    \"status\": 200,\n" +
                                                              "    \"data\": null,\n" +
                                                              "    \"message\": \"사용 가능한 비밀번호입니다.\"\n" +
                                                              "}"),
                            schema = @Schema(implementation = CustomApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "이전 비밀번호와 동일합니다.",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                                              "    \"status\": 400,\n" +
                                                              "    \"data\": null,\n" +
                                                              "    \"message\": \"이전 비밀번호와 동일합니다.\"\n" +
                                                              "}"),
                            schema = @Schema(implementation = CustomApiResponse.class)))
    })
    @PostMapping("check-password")
    ResponseEntity<CustomApiResponse<?>> checkPassword(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "비밀번호 변경 요청 데이터", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CheckPasswordDto.Req.class),
                            examples = @ExampleObject(value = "{\n" +
                                                              "    \"userId\": \"hello\",\n" +
                                                              "    \"pw\": \"abcd1234@!\"\n" +
                                                              "}")))
            @RequestBody CheckPasswordDto.Req dto);

    @Operation(summary = "[회원정보 변경 페이지] 비밀번호 변경")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "비밀번호 변경이 완료되었습니다.",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                                              "    \"status\": 200,\n" +
                                                              "    \"data\": null,\n" +
                                                              "    \"message\": \"비밀번호 변경이 완료되었습니다.\"\n" +
                                                              "}"),
                            schema = @Schema(implementation = CustomApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "이전 비밀번호와 동일합니다.",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                                              "    \"status\": 400,\n" +
                                                              "    \"data\": null,\n" +
                                                              "    \"message\": \"이전 비밀번호와 동일합니다.\"\n" +
                                                              "}"),
                            schema = @Schema(implementation = CustomApiResponse.class))),
            @ApiResponse(responseCode = "401", description = "비밀번호가 일치하지 않습니다.",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                                              "    \"status\": 401,\n" +
                                                              "    \"data\": null,\n" +
                                                              "    \"message\": \"비밀번호가 일치하지 않습니다.\"\n" +
                                                              "}"),
                            schema = @Schema(implementation = CustomApiResponse.class)))
    })
    @PutMapping("password")
    ResponseEntity<CustomApiResponse<?>> changePassword(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "비밀번호 변경 요청 데이터", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ChangePasswordDto.Req.class),
                            examples = @ExampleObject(value = "{\n" +
                                                              "    \"userId\": \"hello\",\n" +
                                                              "    \"pw1\": \"1234\",\n" +
                                                              "    \"pw2\": \"abcd1234@!\"\n" +
                                                              "}")))
            ChangePasswordDto.Req dto);

    @Operation(summary = "[회원탈퇴] 회원탈퇴")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원이 정상적으로 탈퇴되었습니다.",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                                              "    \"status\": 200,\n" +
                                                              "    \"data\": null,\n" +
                                                              "    \"message\": \"회원이 정상적으로 탈퇴되었습니다.\"\n" +
                                                              "}"),
                            schema = @Schema(implementation = CustomApiResponse.class))),
            @ApiResponse(responseCode = "404", description = "해당 사용자를 찾을 수 없습니다.",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                                              "    \"status\": 404,\n" +
                                                              "    \"data\": null,\n" +
                                                              "    \"message\": \"id가 hello인 회원은 존재하지 않습니다.\"\n" +
                                                              "}"),
                            schema = @Schema(implementation = CustomApiResponse.class)))
    })
    @DeleteMapping("{userId}")
    ResponseEntity<CustomApiResponse<?>> withdraw(
            @Parameter(name = "userId", description = "사용자의 아이디", in = ParameterIn.QUERY, required = true, example = "hello")
            @PathVariable("userId") String userId);

}
