import { Link } from 'react-router-dom'
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
import Navbar from '../web/navbar/Navbar'


const Login = () =>
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
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
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
            <Typography component="h1" variant="h5"  sx={{ mt: 6 }} >
              Ingresar
            </Typography>
            <Typography>
              No tienes cuenta? <Link  to='/register' >Registrate!</Link>
            </Typography>
            <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
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
                label="Contraseña"
                type="password"
                id="password"
                autoComplete="current-password"
              />
              <FormControlLabel
                control={<Checkbox value="remember" color="primary" />}
                label="Recuerdame"
              />
              <Button
                theme={ theme }
                type="submit"
                fullWidth
                variant="contained"
                sx={{
                  color:'primary', mt: 3, mb: 2 }}
              >
                Ingresar
              </Button>
              <Grid container>
                <Grid item xs>
                  <Link to="/" variant="body2">
                    olvidaste la contraseña?
                  </Link>
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Grid>
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: 'url(https://source.unsplash.com/random/?city)',
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
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
      </Grid>
    </>
  );
};

export default Login; 