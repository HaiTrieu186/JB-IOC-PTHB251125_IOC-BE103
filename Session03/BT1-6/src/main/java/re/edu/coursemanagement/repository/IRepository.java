package re.edu.coursemanagement.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository <T>{
    List<T> findAll(); //Trả về toàn bộ bản ghi.
    Optional<T> findById(int id); //Trả bản ghi có id khớp.
    T create(T t); //Thêm mới bản ghi.
    T update(int id, T t); //Cập nhật bản ghi theo id.
    T deleteById(int id); //Xoá bản ghi theo id.
}
