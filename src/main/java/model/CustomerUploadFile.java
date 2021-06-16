package model;

import org.springframework.web.multipart.MultipartFile;

public class CustomerUploadFile {
    private int id;
    private String name;
    private String address;
    private MultipartFile img;

    public CustomerUploadFile(String name, String address, MultipartFile img) {
        this.name = name;
        this.address = address;
        this.img = img;
    }

    public CustomerUploadFile(int id, String name, String address, MultipartFile img) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}
