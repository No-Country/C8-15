
import { Stack, Button } from '@mui/material';
import theme from '../../../../themeConfig';



const ButtonHero = () =>{

  return (
    <>
    <Stack direction="row" spacing={2} theme={theme} 
    sx={{
      paddingTop:'25px'
    }}>
    <Button variant="contained" size='large' href="#contained-buttons"  theme={theme}
      sx={{
        borderRadius:'100px',
      }}>
        Buscar ahora
      </Button>
      <Button variant="outlined" size='large' href="#contained-buttons" color='primary' theme={theme}
      sx={{
        borderRadius:'100px',
        border:'3px solid'
      }}>
        Mas Info
      </Button>
    </Stack>
  </>
  )
}; 

export default ButtonHero; 