import React , { useContext } from 'react';
import { Link , useNavigate } from 'react-router-dom';
import { Formik , Field , Form , ErrorMessage } from 'formik';
import { ValidateSchema } from '../../validation/validationForm';
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
    LinearProgress
  } from '@mui/material';
import theme from '../../themeConfig';
import LogoBW from '../../components/web/footer/static/logo blanco viewfinder.png';
import Navbar from '../web/navbar/Navbar'; 
import { postLoginAxios  } from '../../hooks/postAxios';
import Swal from 'sweetalert2'; 
import { AuthContext } from '../../context/AuthContext'



const Login = () => {

  const { login , setLoginStatus } = useContext(AuthContext);
  const initialCredencial = {

    email:'',
    password:''
  }; 

  const navigateRole = ( role , user ) =>{
    switch ( role ) {
      case 'photgrapher':
        return navigate(`/profile/${user}`, { replace:true });
      case 'buyer': 
        return navigate(`/profile-user/${user}`, { replace:true });
      default:
        return null; 
    }
  }; 

  const navigate = useNavigate(); 
  let timerInterval; 
  console.log('DATA LOGIN --->', login ); 

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
              <Formik  
                initialValues={initialCredencial}
                validationSchema={ValidateSchema}
                onSubmit={ async ( values ) => {
                  const profile = await postLoginAxios(values); 
                  console.log(profile); 
                  setLoginStatus();
                  setTimeout(() => {
                    setLoginStatus ? navigate('/profile') : navigate('/register')
                  }, 1000 );
                  return Swal.fire({
                  title:'Bienvenido!',
                  html:`Bienvenido, ${profile.user.name}` ,
                  timer: 2000, 
                  timerProgressBar: true,
                  didOpen: () => {
                    Swal.showLoading();
                    const hi = Swal.getHtmlContainer().querySelector('hi');
                    timerInterval = setInterval(()=> {
                      hi.textContent = Swal.getTimerLeft();
                    }, 100); 
                  },
                  willClose: ()=>{
                    clearInterval(timerInterval);
                  }
                })
              }}
                >
              {({ errors, touched , isSubmitting })=> (
              <Form>
              <Field
                as={ TextField }
                margin="normal"
                required
                fullWidth
                id="email"
                label="Email"
                name="email"
                type='email'
                helperText={ <ErrorMessage name='emial'/>}
                error={ errors.email && touched.email}
                
              />
              <Field
                as={ TextField }
                margin="normal"
                required
                fullWidth
                id="password"
                name="password"
                label="Contraseña"
                type="password"
                helperText={ <ErrorMessage name='password'/>}
                error={ errors.password && touched.password}
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
                <Grid>
                <Grid>
                        { isSubmitting ?(
                        <LinearProgress/>
                        ): null }
                          </Grid>
                </Grid>
              </Grid>
              </Form>
              )}
              </Formik>
            </Box>
          </Box>
        </Grid>
        <Grid
          item
          sm={1}
          md={7}
          sx={{
            widht:'100%',
            height:'100vh',
            display: { xs: 'none', sm: 'block', md: 'block', lg:'block' },
            backgroundImage: 'url(https://source.unsplash.com/random?land)',
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        >
        <CardMedia      
        component="img"
        widht='100'
        height='180'
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