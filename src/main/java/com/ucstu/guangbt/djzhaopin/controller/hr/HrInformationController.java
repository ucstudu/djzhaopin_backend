package com.ucstu.guangbt.djzhaopin.controller.hr;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.HrInformationService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/hrInfos")
public class HrInformationController {

    @Resource
    private HrInformationService hrInformationService;

    @GetMapping("/{hrInfoId}")
    public ResponseEntity<ResponseBody<HrInformation>> getHrInformationByHrInformationId(
            @PathVariable("hrInfoId") @NotNull UUID hrInformationId) {
        return ResponseBody.handle(hrInformationService.getHrInformationByHrInformationId(hrInformationId));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<HrInformation>>> getHrInformations(@PageableDefault Pageable pageable) {
        return ResponseBody.handle(hrInformationService.getHrInformations(pageable));
    }

    @PutMapping("/{hrInfoId}")
    @PreAuthorize("hasPermission(#hrInformationId, 'HrInformation', 'write')")
    public ResponseEntity<ResponseBody<HrInformation>> updateHrInformationByHrInformationId(
            @PathVariable("hrInfoId") @NotNull UUID hrInformationId,
            @Valid @RequestBody HrInformation hrInformation) {
        return ResponseBody.handle(hrInformationService
                .updateHrInformationByHrInformationId(hrInformationId, hrInformation));
    }

}
