import React from 'react'

const ButtonGoogle = () =>
{

    const handleCredentialResponse= (response) => {
      console.log("Encoded JWT ID token: " + response.credential);
    }
    window.onload = function () {
      google.accounts.id.initialize({
        client_id: "YOUR_GOOGLE_CLIENT_ID",
        callback: handleCredentialResponse
      });
      google.accounts.id.renderButton(
        document.getElementById("buttonDiv"),
        { theme: "outline", size: "large" }  // customization attributes
      );
      google.accounts.id.prompt(); // also display the One Tap dialog
    }



  return (
    <>
      <script src="https://accounts.google.com/gsi/client" async defer></script>
      <div id="g_id_onload"
        data-client_id="YOUR_GOOGLE_CLIENT_ID"
        data-login_uri="https://your.domain/your_login_endpoint"
        data-auto_prompt="false">
      </div>
      <div class="g_id_signin"
        data-type="standard"
        data-size="large"
        data-theme="outline"
        data-text="sign_in_with"
        data-shape="rectangular"
        data-logo_alignment="left">
      </div>
      <div id="buttonDiv"></div> 
    </>
  )
}

export default ButtonGoogle