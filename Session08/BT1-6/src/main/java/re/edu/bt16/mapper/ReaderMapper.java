package re.edu.bt16.mapper;

import re.edu.bt16.dto.response.response.ReaderResponse;
import re.edu.bt16.entity.Reader;

public class ReaderMapper {
    public static ReaderResponse mapEntityToResponse(Reader reader) {
        return ReaderResponse.builder()
                .id(reader.getId())
                .email(reader.getEmail())
                .fullName(reader.getFullName())
                .phoneNumber(reader.getPhoneNumber())
                .address(reader.getAddress())
                .avatar(reader.getAvatar())
                .build();
    }
}
