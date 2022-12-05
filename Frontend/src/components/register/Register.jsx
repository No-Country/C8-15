import { Link, useNavigate } from "react-router-dom";
import { Formik , Field , Form , ErrorMessage } from "formik";
import { ValidateSchema } from "../../validation/validationForm";
import { postRegisterAxios } from "../../hooks/postAxios";
import {
  Grid,
  CssBaseline,
  Box,
  Paper,
  Button,
  Typography,
  CardMedia,
  Radio,
  RadioGroup,
  FormControlLabel,
  LinearProgress,
  TextField
} from "@mui/material";
import theme from "../../themeConfig";
import LogoBW from "../../components/web/footer/static/logo blanco viewfinder.png";
import Navbar from "../../components/web/navbar/Navbar";
import Swal from "sweetalert2";




const Register = () => {  
  
  
  const initialCredentials = {
    name: "",
    surname: "",
    email: "",
    password: "",
    passwordConfirm: "",
  };

  const navigate = useNavigate();

  return (
    <>
      <Navbar />
      <Grid container component="main" sx={{ height: "100vh" }}>
        <CssBaseline />
        <Grid
          item
          sm={1}
          md={7}
          sx={{
            widht: "100%",
            height: "100vh",
            display: { xs: "none", sm: "block", md: "block", lg: "block" },
            backgroundImage: "url(https://source.unsplash.com/random?city)",
            backgroundRepeat: "no-repeat",
            backgroundColor: (t) =>
              t.palette.mode === "light"
                ? t.palette.grey[50]
                : t.palette.grey[900],
            backgroundSize: "cover",
            backgroundPosition: "center",
          }}>
          <CardMedia
            component="img"
            widht="1200"
            height="200"
            image={LogoBW}
            alt="logobw"
            sx={{
              display: { sm: "none", md: "none", lg: "block" },
              position: "relative",
              margin: " 300px 0px 200px 0px",
            }}
          />
        </Grid>
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}>
            <Typography component="h1" variant="h5" sx={{ mt: 2 }}>
              Registrate gratis
            </Typography>
            <Typography>
              Ya tienes una cuenta? <Link to="/login">Ingresa </Link>
            </Typography>
            <Formik
              initialValues={initialCredentials}
              validationSchema={ValidateSchema}
              onSubmit={async (values) => {
                try {
                  await postRegisterAxios(values);
                  return Swal.fire(
                    "Registro Completado!",
                    `${values.name}, inicia sesión para continuar.`,
                    "success"
                  ).then(navigate("/login"));
                } catch (error) {
                  alert("Error:", error);
                }
              }}>
                {({  errors , touched , isSubmitting }) =>(
                <Form sx={{ mt: 1 }}>
                  <Box margin="dense" display="flex" justifyContent="center">
                    <RadioGroup
                      row
                      fullWidth
                      aria-labelledby="demo-r-radio-buttons-group-label"
                      defaultValue="fotografo"
                      name="radio-buttons-group">
                      <FormControlLabel
                        value="fotografo"
                        control={<Radio />}
                        label="Fotografo"
                      />
                      <FormControlLabel
                        value="Comprador"
                        control={<Radio />}
                        label="Comprador"
                      />
                    </RadioGroup>
                  </Box>
                  <Grid container spacing={2}>
                    <Grid item xs={12} sm={6}>
                      <Field
                        as={ TextField }
                        margin="dense"
                        required
                        fullWidth
                        id="name"
                        label="Nombre"
                        type="text"
                        name='name'
                        helperText={<ErrorMessage name='name'/>}
                        error={ errors.name && touched.name }

                      />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                      <Field
                        as={ TextField }
                        margin="dense"
                        required
                        fullWidth
                        id="surname"
                        label="Apellido"
                        name="surname"
                        helperText={<ErrorMessage name='surname'/>}
                        error={ errors.surname && touched.surname }
                      />
                    </Grid>
                    <Grid item xs={12}>
                      <Field
                        as={ TextField }
                        margin="dense"
                        required
                        fullWidth
                        id="email"
                        label="Email"
                        name="email"
                        type="email"
                        helperText={<ErrorMessage name='email'/>}
                        error={ errors.email && touched.email }
                      />
                    </Grid>
                    <Grid item xs={12}>
                      <Field
                        as={ TextField }
                        margin="dense"
                        required
                        fullWidth
                        id="password"
                        name="password"
                        label="Contraseña"
                        type="password"
                        helperText={<ErrorMessage name='password'/>}
                        error={  errors.password && touched.password }
                      />
                    </Grid>
                    <Grid item xs={12}>
                      <Field
                        as={ TextField }
                        margin="dense"
                        required
                        fullWidth
                        id="passwordConfirm"
                        name="passwordConfirm"
                        label="Confirmar contraseña"
                        type="password"
                        helperText={<ErrorMessage name='passwordConfirm' />}
                        error={ errors.passwordConfirm && touched.passwordConfirm}
                      />
                    </Grid>
                    <Grid item xs={12}>
                      <Button
                        theme={theme}
                        type='submit'
                        disabled={isSubmitting}
                        fullWidth
                        variant="contained"
                        sx={{
                          color: "primary",
                          mt: 1,
                          mb: 1,
                        }}>
                        Registrarme
                      </Button>
                      <Grid container>
                        
                        <Grid item>
                          <Link to="/login" variant="body2">
                            {"Ya tienes cuenta? Ingresa"}
                          </Link>
                          <Grid>
                        { isSubmitting ?(
                        <LinearProgress/>
                        ): null }
                          </Grid>
                        </Grid>
                      </Grid>
                    </Grid>
                  </Grid>
                </Form>
                )}
            </Formik>
          </Box>
        </Grid>
      </Grid>
    </>
  );
};

export default Register;
