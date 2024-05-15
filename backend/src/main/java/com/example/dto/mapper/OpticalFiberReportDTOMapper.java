package com.example.dto.mapper;

import com.example.dto.OpticalFiberReportDTO;
import com.example.entity.OpticalFiberReportEntity;
import org.springframework.beans.BeanUtils;

public class OpticalFiberReportDTOMapper {
    public static OpticalFiberReportDTO fromOpticalFiberReport(OpticalFiberReportEntity opticalFiberReportEntity) {
        OpticalFiberReportDTO opticalFiberReportDTO = new OpticalFiberReportDTO();
        BeanUtils.copyProperties(opticalFiberReportEntity, opticalFiberReportDTO);
        return opticalFiberReportDTO;
    }

    public static OpticalFiberReportEntity toOpticalFiberReport(OpticalFiberReportDTO opticalFiberReportDTO) {
        OpticalFiberReportEntity opticalFiberReportEntity = new OpticalFiberReportEntity();
        BeanUtils.copyProperties(opticalFiberReportDTO, opticalFiberReportEntity);
        return opticalFiberReportEntity;
    }
}
