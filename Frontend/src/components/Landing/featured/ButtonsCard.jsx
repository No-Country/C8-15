
import { Stack, Button } from '@mui/material';
import theme from '../../../themeConfig';



const ButtonCard = () =>{

  return (
    <>
    <Stack direction="row" spacing={2} theme={theme} 
    sx={{
      paddingTop:'25px'
    }}> 
      <Button variant="outlined" size='small' href="#contained-buttons" color='primary' theme={theme}
      sx={{
        borderRadius:'100px',
        border:'1px solid'
      }}>
        Descartar
      </Button>
    <Button variant="contained" size='small' href="#contained-buttons"  theme={theme}
      sx={{
        borderRadius:'100px',
      }}>Ver más
      </Button>
    </Stack>
  </>
  )
}; 

export default ButtonCard; 