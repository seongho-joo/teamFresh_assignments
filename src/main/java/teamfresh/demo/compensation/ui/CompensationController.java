package teamfresh.demo.compensation.ui;

import static teamfresh.demo.compensation.ui.CompensationResponseMessage.CREATE_COMPENSATION;
import static teamfresh.demo.compensation.ui.CompensationResponseMessage.GET_COMPENSATION;
import static teamfresh.demo.compensation.ui.CompensationResponseMessage.GET_COMPENSATIONS;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamfresh.demo.common.dto.CommonResponse;
import teamfresh.demo.compensation.application.CompensationService;
import teamfresh.demo.compensation.dto.CompensationRequest;
import teamfresh.demo.compensation.dto.CompensationResponse;
import teamfresh.demo.compensation.dto.CompensationsResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/compensations")
public class CompensationController {

    private final CompensationService compensationService;

    @PostMapping
    @ApiOperation(value = "배상 청구 등록")
    public CommonResponse<Long> createCompensation(@Valid @RequestBody CompensationRequest request) {
        compensationService.createCompensation(request);
        return CommonResponse.from(CREATE_COMPENSATION.getMessage());
    }

    @GetMapping("/{compensationId}")
    @ApiOperation(value = "배상 청구 상세보기")
    public CommonResponse<CompensationResponse> getCompensation(@PathVariable Long compensationId) {
        return CommonResponse.of(
            compensationService.getCompensation(compensationId),
            GET_COMPENSATION.getMessage()
        );
    }

    @GetMapping
    @ApiOperation(value = "배상 청구 리스트 조회")
    public CommonResponse<List<CompensationsResponse>> getCompensations() {
        return CommonResponse.of(
            compensationService.getCompensations(),
            GET_COMPENSATIONS.getMessage()
        );
    }
}
