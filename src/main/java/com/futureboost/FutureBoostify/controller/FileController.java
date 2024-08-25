//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/campaigns")
//public class CampaignController {
//
//    @Autowired
//    private CampaignService campaignService;
//
//    @PostMapping
//    public ResponseEntity<ResponseWrapper<CampaignDTO>> createCampaign(
//            @RequestPart("campaignDto") CampaignDTO campaignDto,
//            @RequestPart("mediaFiles") List<MultipartFile> mediaFiles) {
//
//        ResponseWrapper<CampaignDTO> response = campaignService.createCampaign(campaignDto, mediaFiles);
//        return ResponseEntity.ok(response);
//    }
//}
