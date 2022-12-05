import { Link } from 'react-router-dom'; 
import * as React from 'react';
import Navbar from '../../web/navbar/Navbar'
import
{ Grid,
  Container,
  Box,
  Card,
  Avatar,
  Typography,
  Stack,
  IconButton,
  BottomNavigation,
  BottomNavigationAction
} from '@mui/material'
import AddPhotoAlternateSharpIcon from '@mui/icons-material/AddPhotoAlternateSharp';
import EmailIcon from "@mui/icons-material/Email";
import LocalMallIcon from "@mui/icons-material/LocalMall";
import FolderIcon from "@mui/icons-material/Folder";
import RestoreIcon from "@mui/icons-material/Restore";
import { LocationOn } from '@mui/icons-material'
import SettingsIcon from '@mui/icons-material/Settings';
import AvatarImg from '../static/Captura de Pantalla 2022-11-30 a la(s) 21.32.14.png'
import CardPhoto from './cardPhoto'

const ProfileView = () =>{


const [value, setValue] = React.useState("recents");

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };


  return (
    <>
      <Navbar />
      <Card sx={{
        minWidth: 400,
        padding: '100px 100px 30px 100px'
      }} >
        <Box sx={{ p: 6, display: 'flex'}}>
          <Avatar
            alt="Sara Garcia"
            src={AvatarImg}
            sx={{ width: 150, height: 150, margin: '20px 20px 20px 20px' }}
          />
          <Stack spacing={2} sx={{ minWidth: 300 }} >
            <Typography fontSize={24} fontWeight={700} >Sara Garcia 
            <IconButton component={ Link } to='/setting'>
              <SettingsIcon sx={{ fontSize: 28 }} />
            </IconButton></Typography>
            <Typography fontSize={20} fontWeight={500}>Fotografa de paisajes</Typography>
            <Typography fontSize={20} fontWeight={500}>Dog-Lover </Typography>
            <Typography variant="body2" color="text.secondary" sx={{ fontSize: 16, margin:'normal' }}>
              <LocationOn sx={{ color: 'primary' }} /> Mendoza, Arg.
            </Typography>
          </Stack>
        </Box>
      </Card>   
      <Grid>
        <BottomNavigation
          sx={{ width: "100%", height: "40px" }}
          value={value}
          onChange={handleChange}>
          <BottomNavigationAction
            label="Historial"
            value="historial"
            icon={<RestoreIcon fontSize="large" />}
          />
          <BottomNavigationAction
            label="Subir Foto"
            value="favorites"
            component={ Link }
            to='/Upload-photo'
            icon={<AddPhotoAlternateSharpIcon fontSize="large" />} 
          />
          <BottomNavigationAction
            label="Mensajes"
            value="nearby"
            icon={<EmailIcon fontSize="large" />}
          />
          <BottomNavigationAction
            label="vendidos"
            value="buy"
            icon={<LocalMallIcon fontSize="large" />}
          />
          <BottomNavigationAction
            label="Archivo"
            value="folder"
            icon={<FolderIcon fontSize="large" />}
          />
        </BottomNavigation>
      </Grid>
      <Container sx={{ py: 8 , borderShadow:'none'}} maxWidth="lg">
        <CardPhoto />
      </Container>
    </>
  )
}

export default ProfileView; 