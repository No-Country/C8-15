import Navbar from "../../web/navbar/Navbar";
import
{

  IconButton,
  Button,
  Grid,
  Box,
  TextField,
  Chip,
  Autocomplete,
} from "@mui/material";
import PhotoCamera from "@mui/icons-material/PhotoCamera";
import { useEffect, useState } from "react";
import DeleteIcon from '@mui/icons-material/Delete';

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
      <Grid 
        container direction="column"
        justifyContent="flex-start"
        alignItems="center"
        sx={{ padding:'15% 5% 10% 10%' }} >
        <Box 
          cols={12}
          component="form"
          sx={{
            "& .MuiTextField-root": { m: 1, width:'50%' },
          }}
          noValidate
          autoComplete="off">
          <Box>
          <IconButton 
            sx={{ widht: '50px', heigth: '50px' }}
            color="primary"
            aria-label="upload picture"
            component="label">
            <input hidden accept="image/*" type="file" onChange={(e) => handleChange(e.target)} />
            <PhotoCamera fontSize="large" />
          </IconButton>
          </Box>
          {img.map((row, index) => 
            <Box xs={{ margin:'40% 40% 40% 40%'}}
              key={index}>
              <img src={row} alt={row}   Width='60%' height='50%' />
              <Box>
              <Button  
              variant="outlined" 
              onClick={() => deleteImg(row)} 
              startIcon={<DeleteIcon />}
              >
                Eliminar
              </Button>
              </Box>
            </Box>
          )}
          <Box marginTop={5} xs={12}>
          <TextField
            id="filled-basic"
            label="Titulo"
            variant="filled" />
          <TextField
            id="filled-basic"
            label="Fecha"
            variant="filled" />
          <TextField
              id="filled-basic"
              label="Ubicacion"
              variant="filled" />
          <TextField
            id="filled-basic"
            label="Detalle Equipo"
            variant="filled" />
          <TextField
            id="filled-basic"
            label="Link Mercado Pago"
            variant="filled" />
            <TextField
              id="filled-basic"
              label="Precio"
              variant="filled" />
          <TextField
            id="filled-multiline-static"
            label="Descripcion"
            multiline
            rows={4}
            variant="filled"
          />
          <Autocomplete
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
          />
          <Box margin='5% 5% 15% 15%'>
          <Button variant="contained" size='large'>Subir Foto</Button>
          </Box>
          </Box>
        </Box>
      </Grid >
    </>
  );
};

export default UploadPhoto;
