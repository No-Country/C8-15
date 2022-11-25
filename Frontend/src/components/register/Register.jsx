import { Link } from 'react-router-dom';
import
  {
    Grid,
    CssBaseline,
    Box,
    Paper,
    Button,
    TextField,
    FormControlLabel,
    Checkbox,
    Typography,
    CardMedia
  } from '@mui/material'
import theme from '../../themeConfig'
import LogoBW from '../../components/web/footer/static/logo blanco viewfinder.png'
import Navbar from '../../components/web/navbar/Navbar'


const Register = () =>
{


  const handleSubmit = (event) =>
  {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get('email'),
      password: data.get('password'),
    });
  };


  return (
    <>
      <Navbar />
      <Grid container component="main" sx={{ height: '100vh' }} >
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: 'url(https://source.unsplash.com/random/?land)',
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[ 50 ] : t.palette.grey[ 900 ],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        >
          <CardMedia      
        component="img"
        widht='120'
        height='160'
        image= { LogoBW }
        alt='logobw'
        sx={{
          position:'relative',
          margin:' 300px 0px 200px 0px'
        }}
        />
          </Grid>
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Typography component="h1" variant="h5" sx={{ mt: 6 }}>
              Registrate gratis
            </Typography>
            <Typography>
              Ya tienes una cuenta? <Link to='/login' >Ingresa </Link>
            </Typography>
            <Box>
            </Box>
            <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 4 }}>
              <TextField xs={8} sx={{
                width: '25ch',
                mr:1
                }}
                margin="normal"
                required
                id="firstName"
                label="Nombre"
                name="firstName"
                autoComplete="fisrtName"
                autoFocus
              />
              <TextField xs={8} sx={{
                width: '25ch',
                ml:1
              }}
                margin="normal"
                required
                name="lastName"
                label="Apellido"
                type="lastName"
                id="lastName"
                autoComplete="lastName"
              />
              <TextField
                margin="normal"
                required
                fullWidth
                id="email"
                label="Email"
                name="email"
                autoComplete="email"
                autoFocus
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="password2"
                label="Confirmar contraseña"
                type="password2"
                id="password2"
                autoComplete="current-password2"
              />
              <FormControlLabel
                control={<Checkbox value="remember" color="primary" />}
                label=" Estoy de acuerdo a los termino y privacidad"
              />
              <Button
                theme={ theme }
                type="submit"
                fullWidth
                variant="contained"
                sx={{
                  color:'primary',
                  mt: 3, mb: 2 }}
              >
                Registrarme
              </Button>
              <Grid container>
                <Grid item>
                  <Link to='/login' variant="body2">
                    {"Ya tienes cuenta? Ingresa"}
                  </Link>
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Grid>
      </Grid>
    </>
  );
};

export default Register; 