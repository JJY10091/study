    let userid = document.querySelector(".userid");
    let passwd = document.querySelector(".passwd");
    let button = document.querySelector("button");

        //페이지 로딩후 실행되는 자바스크립트
        
        window.onload =() => {
            userid.focus();
        }


        //내부함수 방벙
        // 1번
        // button.onclick = function () {
        //     alert("ok");
        // }

        //화살표 함수
        // 2번
        // button.onclick = () => {
        //     alert("ok");
        // }

        // addevent 함수
        
        // button.addEventListener(이벤트, 함수);
        // 3번
        button.addEventListener('click', function(e) {
            e.preventDefault();
            
            //이벤트 발생시 동작 처리
            if (userid.value ==""){
                alert("아이디를 입력하세요");
                userid.focus();
                return false;
            }

        });

        // 4번
        // button.addEventListener('click', () => {
        //     alert("ok");
        // });

        // 5번
        // input 버튼 누르면 다음 페이지로 이동하는 특징 막는 e.preventDefault
        // button.addEventListener('click', (e) => {
        //     e.preventDefault(); 
        //     alert("ok");
        // });

