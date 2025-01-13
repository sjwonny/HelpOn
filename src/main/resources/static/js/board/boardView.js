//이미지 처리
displayAjax();

function displayAjax(){
    let boardNumber = $('.board-num').val();

    $.ajax({
        url : '/files/imgList',
        type : 'get',
        data : {boardNumber : boardNumber},
        success : function(files){
            let text = '';

            files.forEach(file => {
                let fileName = file.fileUploadPath + '/' + file.fileUuid + '_' + file.fileName;
                text += `
                    <a href="/files/download?fileName=${fileName}"><!--이렇게 a태그만 해놓으면 다운로드가 됨.-->
                      <img src="/files/display?fileName=${fileName}" data-number="${file.fileNumber}" data-name="${fileName}" />
                    </a>
`;
            });

            $('.post-images').html(text); //<div class="post-images">이 안에 텍스트 넣겠다.
        }
    });
}