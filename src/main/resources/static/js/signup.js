// DOMContentLoaded -> html 요소가 다 로드된 후 스크립트 실행
document.addEventListener('DOMContentLoaded', function () {
    const signupForm = document.getElementById('signupForm');
    const submitBtn = document.getElementById('submitBtn');

    if (submitBtn) {
        submitBtn.addEventListener('click', function () {
            const formData = new FormData(signupForm);
            const data = Object.fromEntries(formData.entries());

            fetch('/api/user/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (response.ok) {
                        alert('회원가입이 완료되었습니다!');
                        window.location.href = '/';
                    } else {
                        alert('회원가입 실패: 입력 정보를 확인하세요.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('서버 통신 중 오류가 발생했습니다.');
                });
        });
    }
});
