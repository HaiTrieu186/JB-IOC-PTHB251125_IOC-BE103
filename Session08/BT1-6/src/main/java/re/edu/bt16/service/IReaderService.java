package re.edu.bt16.service;

import re.edu.bt16.dto.request.reader.ReaderCreateDTO;
import re.edu.bt16.dto.response.response.ReaderResponse;

import java.io.IOException;

public interface IReaderService {
    ReaderResponse createReader(ReaderCreateDTO dto) throws IOException;
}
