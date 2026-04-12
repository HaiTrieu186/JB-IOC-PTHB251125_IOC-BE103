package re.edu.bt16.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.edu.bt16.dto.request.reader.ReaderCreateDTO;
import re.edu.bt16.dto.response.response.ReaderResponse;
import re.edu.bt16.entity.Reader;
import re.edu.bt16.exception.ExistEmailException;
import re.edu.bt16.mapper.ReaderMapper;
import re.edu.bt16.repository.IReaderRepository;
import re.edu.bt16.service.IReaderService;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReaderService implements IReaderService {
    private final Cloudinary cloudinary;
    private final IReaderRepository readerRepository;


    @Override
    @Transactional
    public ReaderResponse createReader(ReaderCreateDTO dto) throws IOException {
        if (readerRepository.existsByEmail(dto.getEmail())) {
            throw new ExistEmailException("Lỗi: đã tồn tại người dùng với email trên!");
        }

        Reader reader = new Reader();
        reader.setFullName(dto.getFullName());
        reader.setEmail(dto.getEmail());
        reader.setAddress(dto.getAddress());
        reader.setPhoneNumber(dto.getPhoneNumber());

        if (dto.getAvatarFile() != null && !dto.getAvatarFile().isEmpty()) {
            Map uploadResult = cloudinary.uploader()
                    .upload(dto.getAvatarFile().getBytes(), ObjectUtils.emptyMap());

            // Lấy ra đường dẫn truy cập
            String secureUrl = (String) uploadResult.get("secure_url");

            if (secureUrl == null || secureUrl.isEmpty()) {
                throw new IOException("Lỗi upload: Không tìm thấy URL khi tải ảnh");
            }

            reader.setAvatar(secureUrl);
        } else {
            throw new IllegalArgumentException("Lỗi: Bắt buộc phải tải ảnh lên!");
        }


        reader = readerRepository.save(reader);
        return ReaderMapper.mapEntityToResponse(reader);
    }
}
