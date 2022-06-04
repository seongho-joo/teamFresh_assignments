package teamfresh.demo.penalty.ui;

import static teamfresh.demo.penalty.ui.PenaltyResponseMessage.CREATE_PENALTY;

import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamfresh.demo.common.dto.CommonResponse;
import teamfresh.demo.penalty.application.PenaltyService;
import teamfresh.demo.penalty.dto.PenaltyRequest;

@Slf4j
@RestController
@RequestMapping("/v1/penalty")
@RequiredArgsConstructor
public class PenaltyController {

    private final PenaltyService penaltyService;

    @ApiOperation(value = "패널티 등록 api")
    @PostMapping
    public CommonResponse<Void> createPenalty(@Valid @RequestBody PenaltyRequest request) {
        penaltyService.createPenalty(request);
        return CommonResponse.from(CREATE_PENALTY.getMessage());
    }
}
