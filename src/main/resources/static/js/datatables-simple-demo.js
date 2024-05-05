window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    const datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple, {
            searchable: false,
            
            labels: {
                perPage: 'mục mỗi trang', // Label cho dropdown chọn số lượng item trên mỗi trang
                noRows: 'Không có dữ liệu', // Label hiển thị khi không có dữ liệu
                info: 'Hiển thị {start} đến {end} của {rows} mục' // Label hiển thị thông tin pagination
            }

        });
    }
});
