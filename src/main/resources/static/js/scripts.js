/*!
 * Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
 * Copyright 2013-2023 Start Bootstrap
 * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
 */
// 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }
    var checkboxAll = document.getElementById('checkbox-all');
    if (checkboxAll) {

        var checkboxes = document.querySelectorAll('.table tbody input[type="checkbox"]');

        checkboxAll.addEventListener('change', function () {
            checkboxes.forEach(function (checkbox) {
                checkbox.checked = checkboxAll.checked;
            });
        });
    }

});


function validateForm_user() {
    var maTV = document.getElementById("maTV").value;
    var userName = document.getElementById("user_name").value;
    var userAddress = document.getElementById("user_address").value;
    var nganh = document.getElementById("nganh").value;
    var userPhone = document.getElementById("user_phone").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    


    // Kiểm tra không được để trống
    if (maTV === 0 || userName === "" || userAddress === "" || nganh === "" || userPhone === "" || email === "" || password === "") {
        alert("Vui lòng điền đầy đủ thông tin.");
        return false; // Ngăn chặn việc submit form
    }

    // Kiểm tra Tên Thành Viên, Khoa, Ngành nhập hợp lệ
    var namePattern = /^[\p{L} ]*$/u;
    if (!namePattern.test(userName) ) {
        alert("Tên Thành Viên không hợp lệ. Vui lòng nhập chữ cái và khoảng trắng.");
        return false; // Ngăn chặn việc submit form
    }

    // Kiểm tra số điện thoại là 10 số
    var phonePattern = /^\d{10}$/;
    if (!phonePattern.test(userPhone)) {
        alert("Số điện thoại phải có đúng 10 số.");
        return false; // Ngăn chặn việc submit form
    }

    // Kiểm tra email đúng định dạng
    var emailPattern = /\S+@\S+\.\S+/;
    if (!emailPattern.test(email) || !email.includes('@gmail.com')) {
        alert("Email không đúng định dạng hoặc không phải là gmail.com.");
        return false; // Ngăn chặn việc submit form
    }
    if(document.getElementById("password_cf")){
        var cf_password = document.getElementById("password_cf").value
        if(cf_password != password){
            alert("Yêu cầu nhập lại đúng mật khẩu!");
            return false;
        }
    }
    return true; // Cho phép submit form nếu tất cả điều kiện đều hợp lệ
}




