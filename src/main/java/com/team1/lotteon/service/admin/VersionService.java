package com.team1.lotteon.service.admin;

import com.team1.lotteon.dto.VersionDTO;
import com.team1.lotteon.entity.Version;
import com.team1.lotteon.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class VersionService {

    private final VersionRepository versionRepository;

    private final ModelMapper modelMapper;

    // 버전 insert
    public Version insertVersion(VersionDTO versionDTO) {

        Version version = modelMapper.map(versionDTO, Version.class);

        // 로그인한 사용자 (admin) 객체 id 삽입
        version.setUid("qwer123");

        return versionRepository.save(version);
    }



    // 버전 select (페이징)
    public Page<VersionDTO> getAllVersions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // 페이지 번호와 크기 설정
        Page<Version> versions = versionRepository.findAll(pageable);

        // Version 객체를 VersionDTO로 변환
        return versions.map(version -> modelMapper.map(version, VersionDTO.class));
    }
}
