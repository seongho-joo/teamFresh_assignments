package teamfresh.demo.voc.ui;

import static teamfresh.demo.voc.ui.VocResponseMessage.CREATE_VOC;
import static teamfresh.demo.voc.ui.VocResponseMessage.GET_VOC;
import static teamfresh.demo.voc.ui.VocResponseMessage.GET_VOC_LIST;

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
import teamfresh.demo.voc.application.VocService;
import teamfresh.demo.voc.dto.VocRequest;
import teamfresh.demo.voc.dto.VocResponse;
import teamfresh.demo.voc.dto.VocsResponse;

@Slf4j
@RestController
@RequestMapping("/v1/vocs")
@RequiredArgsConstructor
public class VocController {

    private final VocService vocService;

    @PostMapping
    @ApiOperation(value = "voc 등록")
    public CommonResponse<Void> createVoc(@Valid @RequestBody VocRequest request) {
        vocService.createVoc(request);
        return CommonResponse.from(CREATE_VOC.getMessage());
    }

    @GetMapping("/{vocId}")
    @ApiOperation(value = "voc 상세 조회")
    public CommonResponse<VocResponse> getVoc(@PathVariable Long vocId) {
        return CommonResponse.of(vocService.getVoc(vocId), GET_VOC.getMessage());
    }

    @GetMapping
    @ApiOperation(value = "voc 목록 조회")
    public CommonResponse<List<VocsResponse>> getVocLists() {
        return CommonResponse.of(vocService.getVocLists(), GET_VOC_LIST.getMessage());
    }
}
