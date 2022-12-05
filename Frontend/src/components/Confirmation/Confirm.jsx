import Navbar from '../web/navbar/Navbar'
import Footer from '../web/footer/Footer'
import { Container,
        Paper, 
        Typography,
        Grid, 
        List,
        ListItem,
        ListItemText,
        Button,
        Box 
} from '@mui/material'
import theme from '../../themeConfig.js'
import { Link } from 'react-router-dom'

const Confirm = () => {
  return (
    <>
    <Navbar/>
    <Container component="main" maxWidth="sm" sx={{ mb: 8 }}>
        <Paper variant="outlined" sx={{ my: { xs: 3, md: 6 }, p: { xs: 2, md: 3 } }}>
          <Typography component="h1" variant="h4" align="center">
            Resumen Compra 
          </Typography>
          <List disablePadding>
          <ListItem key="" sx={{ py: 1, px: 0 }}>
            <ListItemText primary="" />
            <Typography variant="body2">{}</Typography>
          </ListItem>
        <ListItem sx={{ py: 1, px: 0 }}>
          <ListItemText primary="Total" />
          <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
            $34.06
          </Typography>
        </ListItem>
      </List>
      <Grid container spacing={2}>
        <Grid item xs={12} sm={6}>
          <Typography variant="h6" gutterBottom sx={{ mt: 2 }}>
            Vendedor
          </Typography>
          <Typography gutterBottom>John Smith</Typography>
          <Typography gutterBottom>{}</Typography>
        </Grid>
        <Grid item container direction="column" xs={12} sm={6}>
          <Typography variant="h6" gutterBottom sx={{ mt: 2 }}>
            Detalle Pago
          </Typography>
            <Typography gutterBottom>Mercado Pago</Typography>
        </Grid>
        <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                  <Button theme={theme} color='primary' component={ Link } to='/Profile' sx={{ mt: 3, ml: 1 }}>
                  volver 
                  </Button>
                  <Button theme={theme} 
                  bg='secondary'
                  variant='contained'
                  sx={{ mt: 3, ml: 1 }}
                >Descargar Foto
                </Button>
        </Box>
        </Grid>
        </Paper>
    </Container>
    <Footer/>
    </>
  )
}
export default Confirm; 


