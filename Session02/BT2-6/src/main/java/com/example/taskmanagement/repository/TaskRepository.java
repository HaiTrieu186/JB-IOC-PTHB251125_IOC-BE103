package com.example.taskmanagement.repository;

import com.example.taskmanagement.model.PriorityEnum;
import com.example.taskmanagement.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private final List<Task> tasks;

    public TaskRepository() {
        tasks = new ArrayList<>();

        tasks.add(new Task("T1", "Làm đăng nhập", "Xử lý chức năng đăng nhập", PriorityEnum.HIGH, "U1"));
        tasks.add(new Task("T2", "Làm đăng ký", "Xử lý chức năng đăng ký", PriorityEnum.MEDIUM, "U2"));
        tasks.add(new Task("T3", "Quên mật khẩu", "Chức năng lấy lại mật khẩu", PriorityEnum.LOW, "U3"));
        tasks.add(new Task("T4", "Trang chủ", "Hiển thị trang chủ", PriorityEnum.MEDIUM, "U1"));
        tasks.add(new Task("T5", "Danh sách user", "Hiển thị danh sách người dùng", PriorityEnum.LOW, "U2"));
        tasks.add(new Task("T6", "Thêm user", "Chức năng thêm người dùng", PriorityEnum.MEDIUM, "U3"));
        tasks.add(new Task("T7", "Sửa user", "Chức năng sửa thông tin user", PriorityEnum.HIGH, "U1"));
        tasks.add(new Task("T8", "Xóa user", "Chức năng xóa người dùng", PriorityEnum.HIGH, "U2"));
        tasks.add(new Task("T9", "Tìm kiếm", "Tìm kiếm user theo tên", PriorityEnum.LOW, "U3"));
        tasks.add(new Task("T10", "Phân quyền", "Gán quyền cho user", PriorityEnum.MEDIUM, "U1"));
    }

    public List<Task> findAll() {
        return tasks;
    }

    public Task add(Task task) {
        tasks.add(task);
        return task;
    }
}
