import { Link } from 'react-router-dom';
import { useFormik } from 'formik';
import { ValidateSchema } from '../../validation/validationForm';
import axios from 'axios';
import
{
  Grid,
  CssBaseline,
  Box,
  Paper,
  Button,
  Typography,
  CardMedia,
  TextField,

} from '@mui/material';
import theme from '../../themeConfig';
import LogoBW from '../../components/web/footer/static/logo blanco viewfinder.png';
import Navbar from '../../components/web/navbar/Navbar';
import authRest from '../../services/authRest';






const Register = () =>
{

const initialCredentials = {
  name: '',
  surname: '',
  email: '',
  password: '',
  passwordConfirm: ''
};


  const formik = useFormik({

    initialValues: initialCredentials,
    validationSchema: ValidateSchema,

    onSubmit: async (values ,{ setSubmitting }) => {
      await new Promise((res) => setTimeout(res, 1800));
      localStorage.setItem('User temp', values);
      alert(JSON.stringify(values, null, 2 ));
      setSubmitting(false);
      try {
        const { data } = await authRest.register(values);
        alert(data.message);
        window.location.replace('/');
        return true;
      } catch ({ response })
      {
        alert(response.data.message);
      }
    }

  })


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
            display: { xs: 'none' },
            backgroundImage: 'url(https://source.unsplash.com/random)',
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
            image={LogoBW}
            alt='logobw'
            sx={{
              position: 'relative',
              margin: ' 300px 0px 200px 0px'
            }}
          />
        </Grid>
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              mb: 10,
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
              <form  onSubmit={formik.handleSubmit}>
                <Box  sx={{ xl:{ m: 1, width: '25ch' }
                }}>
                  <TextField 
                    margin='normal'
                    required
                    fullWidth 
                    id="name"
                    label="Nombre"
                    name="name"
                    value={formik.values.name}
                    onChange={formik.handleChange}
                    error={formik.touched.name && Boolean(formik.errors.name)}
                    helperText={formik.touched.name && formik.errors.name}
                    autoComplete="name"
                  />
                  <TextField 
                    margin='normal'
                    required
                    fullWidth
                    id="surname"
                    label="Apellido"
                    name="surname"
                    type="surname"
                    value={formik.values.surname}
                    onChange={formik.handleChange}
                    error={formik.touched.surname && Boolean(formik.errors.surname)}
                    helperText={formik.touched.surname && formik.errors.surname}
                    autoComplete="surname"
                  />
                  </Box>
                  <TextField
                    margin='normal'
                    required
                    fullWidth
                    id="email"
                    label="Email"
                    name="email"
                    type='email'
                    value={formik.values.email}
                    onChange={formik.handleChange}
                    error={formik.touched.email && Boolean(formik.errors.email)}
                    helperText={formik.touched.email && formik.errors.email}
                  />
                  <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="password"
                    name="password"
                    label="Contraseña"
                    type="password"
                    value={formik.values.password}
                    onChange={formik.handleChange}
                    error={formik.touched.password && Boolean(formik.errors.password)}
                    helperText={formik.touched.password && formik.errors.password}
                    autoComplete="current-password"
                  />
                  <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="passwordConfirm"
                    name="passwordConfirm"
                    label="Confirmar contraseña"
                    type="password"
                    value={formik.values.passwordConfirm}
                    onChange={formik.handleChange}
                    error={formik.touched.passwordConfirm && Boolean(formik.errors.passwordConfirm)}
                    helperText={formik.touched.passwordConfirm && formik.errors.passwordConfirm}
                    autoComplete="current-passwordConfirm"
                  />
                  <Button
                    theme={theme}
                    type="submit"
                    fullWidth
                    variant="contained"
                    sx={{
                      color: 'primary',
                      mt: 3, mb: 2
                    }}
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
              </form>
            </Box>
          </Box>
        </Grid>
      </Grid>
    </>
  )
};



export default Register; 