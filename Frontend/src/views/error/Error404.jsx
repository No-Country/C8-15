import img404 from './static/404-Error-rafiki.jpeg';
import Navbar from "../../components/web/navbar/Navbar";
import { Grid } from '@mui/material'


const Error404 = () => {

  return(
  <>
  <Navbar/>  
  <Grid item xl="auto" maxwidth='xl'
      sx={{
        position: 'relative',
        top: 0,
        bottom: 0,
        right: 0,
        left: 0,
        paddingLeft:0,
        paddingRight:0
      }}
      >
  <div maxwidth='xs' style={{
        backgroundImage:`URL(${img404}`,
        backgroundRepeat:'no-repeat',
        backgroundPosition:'center',
        backgroundSize:'650px 550px',
        minHeight:'100vh',
        }}>
    </div>
  </Grid>
  </>
  )

}

export default Error404; 