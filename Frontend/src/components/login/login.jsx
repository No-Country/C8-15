import { Link } from 'react-router-dom'
import { useFormik } from 'formik'
import { ValidateSchema } from '../../validation/validationForm'
import axios from 'axios'
import
  {
    Grid,
    CssBaseline,
    Box,
    Paper,
    Button,
    TextField,
    Typography,
    CardMedia,
  } from '@mui/material'
import theme from '../../themeConfig'
import LogoBW from '../../components/web/footer/static/logo blanco viewfinder.png'
import Navbar from '../web/navbar/Navbar'



const initialCredentials = {
    email:'',
    password:'',
    //remenberMe:false,
};


const Login = () =>
{
    const formik = useFormik({

      initialValues : initialCredentials,
      validationShema: ValidateSchema,

      onSubmit: async (values) => {
          await new Promise((res) => setTimeout(res, 400));
          alert(JSON.stringify(values));
    try {
      const { data } = await axios.post(
        `${apiClient}/users`, 
        values
      );
    
    alert(data.message);
    window.location.replace('/');
    return data.message;
  } catch ({ response }) {
    alert(response.data.message);
  }
}
})

  


  return (
    <>
      <Navbar/>
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
            <Box sx={{ mt: 1 }}>
              <form  onSubmit={formik.handleSubmit}>
              <TextField
                margin="normal"
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
              </form>
            </Box>
          </Box>
        </Grid>
        <Grid
          item
          sx={{
            display:{ xs:'none'},
            backgroundImage: 'url(https://source.unsplash.com/random)',
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