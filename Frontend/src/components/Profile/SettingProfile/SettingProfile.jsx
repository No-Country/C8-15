import Navbar from "../../web/navbar/Navbar";
import { Grid,
        Typography,
        TextField,
        Button, 
        Box,
        Container,
        Avatar, 
        Stack
      } from '@mui/material'
import { Link } from 'react-router-dom'
import theme from '../../../themeConfig'


const SettingProfile = () => {


  return (
    <>
    <Navbar fixed />
    <Container component="main" maxWidth="sm" sx={{ mb: 4 , mt:15}}>
    <Typography variant="h6" gutterBottom>
        Perfil Usuario 
      </Typography>
      <Grid item xs={6}>
      <Stack>
      <Avatar
        alt="user"
        src="../static/Captura de Pantalla 2022-11-30 a la(s) 21.32.14.png"
        sx={{ width: 70, height: 70}}
      />
      </Stack>
      </Grid>
        <Grid item xs={12}>
          <TextField
            margin="normal"
            required
            id="name"
            name="name"
            label="Nombre"
            fullWidth
            autoComplete="given-name"
            variant="filled"
          />
        </Grid>
        <Grid item xs={12} >
          <TextField
          margin="normal"
            required
            id="surame"
            name="surame"
            label="Apellido"
            fullWidth
            autoComplete="family-name"
            variant="filled"
          />
        </Grid>
        <Grid item xs={12} >
          <TextField
          margin="normal"
            required
            id="username"
            name="username"
            label="User Name"
            fullWidth
            autoComplete="family-name"
            variant="filled"
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
          margin="normal"
            required
            type='email'
            id="email"
            name="emial"
            label="Email"
            fullWidth
            variant="filled"
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
          margin="normal"
            required
            id="country"
            name="country"
            label="País"
            fullWidth
            autoComplete="shipping country"
            variant="filled"
          />
          </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
          margin="normal"
            id="city"
            name="city"
            label="Ciudad"
            fullWidth
            autoComplete="shipping address-level2"
            variant="filled"
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
          margin="normal"
            id="phone"
            name="phone"
            label="Telefono Contacto"
            fullWidth
            variant="filled"
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
          margin="normal"
            id="password"
            name="password"
            label="Cambiar Contraseña"
            fullWidth
            autoComplete="contraseña"
            variant="filled"
          />
        </Grid>
        <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                  <Button theme={theme} color='primary' component={ Link } to='/Profile' sx={{ mt: 3, ml: 1 }}>
                  volver 
                  </Button>
                  <Button theme={theme} 
                  bg='secondary'
                  variant='contained'
                  sx={{ mt: 3, ml: 1 }}
                >Guardar
                </Button>
        </Box>
        </Container>
    </>
  )
}

export default SettingProfile;