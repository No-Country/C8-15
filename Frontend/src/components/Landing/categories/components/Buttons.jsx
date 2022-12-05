
import { Button } from '@mui/material'
import theme from '../../../../themeConfig'


const Buttons = () => {

  const categories = [ 'Callejera', 'Comida', 'Retrato', 'Aquitectura','Mascotas','Deportes','Social','Acuatica','Naturaleza','Productos','Bodas','Macro']



  return (
    <>
    {categories.map((categories) => (
              <Button theme={theme} variant="outlined"
              sx={{
                color:'primary',
                font:'Nunito',
                fontSize:'14px',
                margin: '15px 15px 15px 15px',
                borderRadius:'100px'
              }}    
              key={categories}
              >
                {categories}
              </Button>
            ))}
</>
  )
}

export default Buttons