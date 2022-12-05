
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
    </Stack>
  </>
  )
}; 

export default ButtonHero; 