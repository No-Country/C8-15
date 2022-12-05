import Navbar from "../../web/navbar/Navbar";
import
{ Container,
  IconButton,
  Button,
  Grid,
  Box,
  TextField,
  Chip,
  Autocomplete,
  Typography
} from "@mui/material";
import PhotoCamera from "@mui/icons-material/PhotoCamera";
import { useEffect, useState } from "react";
import DeleteIcon from '@mui/icons-material/Delete';
import { Link } from 'react-router-dom'
import theme from '../../../themeConfig'

const UploadPhoto = () =>
{

  const Categories = [
    'Documental',
    'Urbana',
    'Animales',
    'Macro',
    'Retrato',
    'Abstracto',
    'Astrofotografia',
    'Publicitaria',
    'Productos',
    'Naturaleza'
  ]


  const [ img, setImg ] = useState([]);

  const handleChange = (e) =>
  {
    setImg((img) => [ ...img, URL.createObjectURL(e.files[ 0 ]) ]);
    return URL.revokeObjectURL(e.files[ 0 ])
  }

  const deleteImg = (blob) =>
  {
    setImg(img.filter(x => x !== blob));
  };

  useEffect(() =>
  {
    console.log(img);
  }, [ img ]);


  return (
    <>
      <Navbar />
      <Container component="main" maxWidth="sm" sx={{ mb: 4 , mt:15}}>
      <Typography variant="h6" gutterBottom>
      Subir Fotografia 
      </Typography>
      
          <IconButton theme={theme}
            sx={{ widht: '50px', heigth: '50px' }}
            color="primary"
            aria-label="upload picture"
            component="label">
            <input hidden accept="image/*" type="file" onChange={(e) => handleChange(e.target)} />
            <PhotoCamera fontSize="large" />
          </IconButton>
          {img.map((row, index) => 
            <Box xs={{ margin:'40% 40% 40% 40%'}}
              key={index}>
              <img src={row} alt={row}   Width='60%' height='50%' />
              <Box>
              <Button 
              theme={theme}
              color='primary' 
              variant="outlined" 
              onClick={() => deleteImg(row)} 
              startIcon={<DeleteIcon />}
              >
                Eliminar
              </Button>
              </Box>
            </Box>
          )}


          <Grid spacing={3} >
          <Grid item xs={12}>
          <TextField
          margin="normal"
            id="filled-basic"
            label="Titulo"
            fullWidth
            variant="filled" />
            </Grid>
          <Grid item xs={12} >
          <TextField
          margin="normal"
            fullWidth
            id="filled-basic"
            label="Fecha"
            variant="filled" />
            </Grid>
          <Grid item xs={12}>
          <TextField
          margin="normal"
          fullWidth
              id="filled-basic"
              label="Ubicacion"
              variant="filled" />
              </Grid>
          <Grid item xs={12} >
          <TextField
          margin="normal"
          fullWidth
            id="filled-basic"
            label="Detalle Equipo"
            variant="filled" />
            </Grid>
          <Grid item xs={12}>
          <TextField
          margin="normal"
          fullWidth
            id="filled-basic"
            label="Link Mercado Pago"
            variant="filled" />
            </Grid>
          <Grid item xs={12} >
            <TextField
            margin="normal"            fullWidth
              id="filled-basic"
              label="Precio"
              variant="filled" />
              </Grid>
          <Grid item xs={12} >
          <TextField
          margin="normal"
          fullWidth
            id="filled-multiline-static"
            label="Descripcion"
            multiline
            rows={4}
            variant="filled"
          />
          </Grid>
          <Grid item xs={12}>
          <Autocomplete
          margin="normal"
            multiple
            id="tags-filled"
            options={Categories.map((Categories) => Categories)}
            freeSolo
            renderTags={(value, getTagProps) =>
              value.map((option, index) => (
                <Chip variant="outlined" label={option} {...getTagProps({ index })} />
              ))
            }
            renderInput={(params) => (
              <TextField
                {...params}
                variant="filled"
                label="Categorias"
                placeholder="Favorites"
              />
            )}
          /></Grid>
          <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
          <Button theme={theme} color='primary' component={ Link } to='/Profile' sx={{ mt: 3, ml: 1 }}>
                  volver 
                  </Button>
          <Button theme={theme} bg='secondary' variant="contained" size='medium'
          sx={{ mt: 3, ml: 1 }}>Subir Foto</Button>
          </Box>
        </Grid>
      </Container>
    </>
  );
};

export default UploadPhoto;
