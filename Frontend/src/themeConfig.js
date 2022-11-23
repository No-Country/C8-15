import { createTheme } from '@mui/material'; 

const theme = createTheme({

    palette:{
      primary:{
        main:'#025373'

      },
      secondary:{
        main:'#FFFFFF'
      },
      
      divider:{
        main:'#FFFFFF',
        opacity:'0.5'
      }
    },
    typography:{
      fontFamily:"Nunito",
      titleHero:{
        fontSize:48,
      },
      subtitle1:{
        fontSize:24,
      }
    }

})


export default theme; 