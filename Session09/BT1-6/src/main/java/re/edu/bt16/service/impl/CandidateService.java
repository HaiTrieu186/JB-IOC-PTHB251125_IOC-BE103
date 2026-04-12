package re.edu.bt16.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.edu.bt16.dto.request.CandidateApplyDTO;
import re.edu.bt16.dto.response.CandidateResponse;
import re.edu.bt16.entity.Candidate;
import re.edu.bt16.exception.ExceedFileSizeExtension;
import re.edu.bt16.mapper.CandidateMapper;
import re.edu.bt16.repository.ICandidateRepository;
import re.edu.bt16.service.ICandidateService;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CandidateService implements ICandidateService {
    private final Cloudinary cloudinary;
    private final ICandidateRepository candidateRepository;

    @Override
    @Transactional
    public CandidateResponse createCandidate(CandidateApplyDTO dto) throws IOException {
        Candidate candidate = new Candidate();


        String secureUrl="";
        if (dto.getCvFile() != null && !dto.getCvFile().isEmpty()) {

            if (dto.getCvFile().getSize() > 10 *1024 * 1024) {
                throw new ExceedFileSizeExtension("Lỗi: Kích thước file CV phải nhỏ hơn 10MB");
            }

            Map uploadResult = cloudinary.uploader()
                    .upload(dto.getCvFile().getBytes(), ObjectUtils.emptyMap());

            // Lấy ra đường dẫn truy cập
            secureUrl = (String) uploadResult.get("secure_url");

            if (secureUrl == null || secureUrl.isEmpty()) {
                throw new IOException("Lỗi upload: Không tìm thấy URL khi tải ảnh");
            }
        } else {
            throw new IllegalArgumentException("Lỗi: Bắt buộc phải tải CV lên!");
        }

        candidate.setName(dto.getName());
        candidate.setEmail(dto.getEmail());
        candidate.setCvUrl(secureUrl);

        candidate = candidateRepository.save(candidate);

        return CandidateMapper.mapEntityToResponse(candidate);
    }
}
