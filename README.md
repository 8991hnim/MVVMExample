# MVVMExample
a demo project use mvvm architecture


## 1. Ưu điểm MVVM
  
1. Thành phần giao diện tách riêng với xử lý logic
2. Thành phần xử lý logic được tách riêng với nơi lấy dữ liệu
3. Vòng đời của app được duy trì
4. Dễ test, bảo trì

## 2. Thành phần MVVM

![alt text](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

### Repositories
Repository xử lý việc gửi nhận dữ liệu. Tạo repository là 1 cách làm tốt để phân biệt rõ ràng giữa cách ứng dụng nhận dữ liệu và cách nó sử dụng/ hiển thị dữ liệu

Repository biết sẽ phải lấy dữ liệu từ ở đâu (ví dụ query api, query database) 

Để ý rằng mỗi thành phần chỉ phụ thuộc vào 1 thành phần bên dưới nó. Đây là 1 phần rất quan trọng trong tối ưu hóa cấu trúc code. Repository đóng vai trò là 1 cổng cho việc trao đổi dữ liệu

### View Models
ViewModel à nơi tiếp nhận dữ liệu (từ repository) và xử lý logic liên quan. ViewModel sẽ tồn tại trong bộ nhớ đến khi vòng đời mà nó gắn vào trước đó bị hủy bỏ hoàn toàn. Trong trường hợp activity là khi activity bị finish, còn trong trường hợp fragment là khi nó bị detach. 

### View
Là activity/fragment - nơi cập nhật giao diện. Nó sẽ không biết dữ liệu đến từ đâu, được xử lý logic ra sao mà chỉ quan tâm sẽ cập nhật giao diện như thế nào với dữ liệu được trả về từ viewmodel.

## 3. Demo

### Dpendencies cần thiết

```  
    //retrofit
    def retrofit_version = "2.7.1"
    implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"

    // ViewModel and LiveData
    def lifecycle_version = "2.1.0"
    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"

    //glide
    def glideVersion = "4.9.0"
    implementation "com.github.bumptech.glide:glide:$glideVersion"
    annotationProcessor "com.github.bumptech.glide:compiler:$glideVersion"

    //design support
    def supportVersion = "28.0.0"
    implementation "com.android.support:design:$supportVersion"

```



