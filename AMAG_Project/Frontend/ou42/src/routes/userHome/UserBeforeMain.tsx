/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { useNavigate } from "react-router-dom";

const container = css`
  display: flex;
  height: 100vh;
  flex-direction: column;
  align-items: center;
  position: relative;

  #svg {
    position: fixed;
    top: 15%;
  }

  .font {
    text-align: center;
    bottom: 20%;
    position: absolute;
    top: 38%;

    p:nth-of-type(1) {
      font-weight: 900;
      font-size: 1.3rem;
      margin-bottom: 5%;
    }
    p:nth-of-type(2) {
      margin: 0;
    }
  }

  .btn {
    position: absolute;
    border: 0;
    width: 50%;
    height: 5%;
    border-radius: 10px;
    background-color: #ffabab;
    color: white;
    font-weight: 900;
    font-size: 1rem;
    bottom: 20%;
    box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.54);
  }

  .login {
    position: absolute;
    bottom: 16%;
    display: flex;

    p {
      margin: 0;
      font-size: 0.8rem;
    }

    p:nth-of-type(1) {
      opacity: 0.3;
      margin: 0 10px 0 0;
    }

    p:nth-of-type(2) {
      color: #d14d72;
      font-weight: 900;
    }
  }
`;

function UserBeforeMain() {
  const navigate = useNavigate();

  const goLogin = () => {
    navigate("/login");
  };

  const goSignUp = () => {
    navigate("/signup");
  };

  return (
    <div css={container}>
      <svg width="250" height="250" id="svg" viewBox="0 0 324 324" fill="none">
        <rect width="324" height="324" fill="url(#pattern0)" />
        <rect width="324" height="324" fill="url(#pattern1)" />
        <defs>
          <pattern
            id="pattern0"
            patternContentUnits="objectBoundingBox"
            width="1"
            height="1"
          >
            <use
              xlinkHref="#image0_251_414"
              transform="translate(-0.0689655) scale(0.00229885)"
            />
          </pattern>
          <pattern
            id="pattern1"
            patternContentUnits="objectBoundingBox"
            width="1"
            height="1"
          >
            <use
              xlinkHref="#image0_251_414"
              transform="translate(-0.0689655) scale(0.00229885)"
            />
          </pattern>
          <image
            id="image0_251_414"
            width="495"
            height="435"
            xlinkHref="data:image/png;base64,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"
          />
        </defs>
      </svg>

      <div className="font">
        <p>비대면 물품 공유 플랫폼</p>
        <p>우리서비스는 이런거에요 설명문구 두줄정도!</p>
      </div>

      <button className="btn" onClick={goLogin}>
        시작하기
      </button>

      <div className="login">
        <p>계정이 없으신가요?</p>
        <p onClick={goSignUp}>회원가입</p>
      </div>
    </div>
  );
}

export default UserBeforeMain;
