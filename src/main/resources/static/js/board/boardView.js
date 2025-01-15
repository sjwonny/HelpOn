import * as reply from '../module/reply.js';
//모듈 경로는 일반적으로 상대경로로 접근한다.
const boardNumber = $('.board-num').val();
let page = 1; //기본 페이지는 1

reply.getListPage({boardNumber : boardNumber, page : page}, showReply, showError);

function showReply(map){
    //console.log(map);

    let text = '';

    map.replyList.forEach(r=> {
        text +=`
        <div class="reply" data-num="${r.replyNumber}">
            <div class="reply-box">
              <div class="reply-box__writer">${r.userId}</div>
              <div class="reply-box__content">${r.replyContent}</div>
            </div>
        </div>
        <div class="reply-time">
        ${reply.timeForToday(r.replyRegisterDate == r.replyUpdateDate ? r.replyRegisterDate : r.replyUpdateDate)}
        </div>
        <div class = "reply-btn-box">
        `;

        if(r.userNumber == loginNumber){
            text += `    
                <span class="reply-btns"></span>
                <div class="reply-btns__box none">
                  <div class="reply-remove-btn">삭제</div>
                  <div class="reply-modify-btn">수정</div>
                </div>`;
        }

        text += `
            </div>
        </div>
        `;
    });
    $('.reply-list-wrap').html(text);
}
//리플 작성 완료 처리
$('brn-reply').on('click', function(){
    let content  = $('#reply-content').val();

    let replyObj = {
        replyContent : content,
        boardNumber : boardNumber
    }
})





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
                    <a href="/files/download?fileName=${fileName}"> <!--이렇게 a태그만 해놓으면 다운로드가 됨.-->
                      <img src="/files/display?fileName=${fileName}" data-number="${file.fileNumber}" data-name="${fileName}" />
                    </a> `;
            });
            $('.post-images').html(text); //<div class="post-images">이 안에 텍스트 넣겠다.
        }
    });
}
//뒤로가기 버튼
$('.btn-back').on('click',function(){
    window.location.href='/board/list';
})



