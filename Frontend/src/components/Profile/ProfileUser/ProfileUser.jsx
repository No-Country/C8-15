import Navbar from "../../web/navbar/Navbar";
import * as React from "react";
import { Link } from "react-router-dom";
import Footer from "../../web/footer/Footer";
import {
  Grid,
  Card,
  Box,
  Avatar,
  Stack,
  Typography,
  IconButton,
  ListSubheader,
  BottomNavigation,
  BottomNavigationAction,
  ImageListItem,
  ImageListItemBar,
} from "@mui/material";
import EmailIcon from "@mui/icons-material/Email";
import LocalMallIcon from "@mui/icons-material/LocalMall";
import FolderIcon from "@mui/icons-material/Folder";
import RestoreIcon from "@mui/icons-material/Restore";
import FavoriteIcon from "@mui/icons-material/Favorite";
import { LocationOn } from "@mui/icons-material";
import SettingsIcon from "@mui/icons-material/Settings";
import InfoIcon from "@mui/icons-material/Info";
import AvatarImg from "../static/Captura de Pantalla 2022-11-30 a la(s) 21.32.14.png";
import Photos from "../profilePhotografer/photos"
import theme from "../../../themeConfig.js";
import Masonry from "@mui/lab/Masonry";

const ProfileUser = () => {
  const [value, setValue] = React.useState("recents");

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <>
      <Navbar />
      <Grid container spacing={2} theme={theme}>
        <Box sx={{ flexGrow: 1 }}>
          <Card
            sx={{
              minWidth: 300,
              padding: "70px 70px 30px 70px",
            }}>
            <Box
              sx={{
                p: 6,
                display: "flex",
                flex: { xs: "center", sm: "center" },
              }}>
              <Avatar
                alt="Sara Garcia"
                src={AvatarImg}
                sx={{ width: 150, height: 150, margin: "20px 20px 20px 20px" }}
              />
              <Stack spacing={2} sx={{ minWidth: 200 }}>
                <Typography fontSize={24} fontWeight={700}>
                  Sara Garcia
                  <IconButton component={ Link } to="/setting">
                    <SettingsIcon sx={{ fontSize: 28 }} />
                  </IconButton>
                </Typography>
                <Typography fontSize={20} fontWeight={500}>
                  Fotografa de paisajes
                </Typography>
                <Typography fontSize={20} fontWeight={500}>
                  Dog-Lover{" "}
                </Typography>
                <Typography
                  variant="body2"
                  color="text.secondary"
                  sx={{ fontSize: 16, margin: "normal" }}>
                  <LocationOn sx={{ color: "primary" }} /> Mendoza, Arg.
                </Typography>
              </Stack>
            </Box>
          </Card>
          <Grid>
        <BottomNavigation
          sx={{ width: "100%", height: "80px" }}
          value={value}
          onChange={handleChange}>
          <BottomNavigationAction
            label="Historial"
            value="historial"
            icon={<RestoreIcon fontSize="large" />}
          />
          <BottomNavigationAction
            label="Favoritos"
            value="favorites"
            icon={<FavoriteIcon fontSize="large" />}
          />
          <BottomNavigationAction
            label="Mensajes"
            value="nearby"
            icon={<EmailIcon fontSize="large" />}
          />
          <BottomNavigationAction
            label="Compras"
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
      
      <Grid
        container
        sx={{
          alignItems: "center",
          justifyContent: "center",
          margin: "auto",
          padding: "20px 20px 20px 20px",
        }}>
        <ListSubheader
          component="div"
          sx={{ fontSize: "40px", marginTop: "20px", marginBottom: "20px" }}>
          Favoritos
        </ListSubheader>
        <Masonry
          columns={{ xs: 1, sm: 2, md: 3, lg: 3, xl: 5 }}
          spacing={{ xs: 1, sm: 2, lg: 2 }}
          rowHeight={190}
          variant="quilted"
          sx={{
            width: "90%",
            height: "100%",
          }}>
          {Photos.map(({ url, id, title, description }) => (
            <ImageListItem key={id}>
              <img
                src={url}
                srcSet={url}
                alt={title}
                loading="eager"
                sx={{
                  width: "50%",
                }}
              />
              <ImageListItemBar
                title={title}
                subtitle={description}
                actionIcon={
                  <IconButton
                    sx={{ color: "rgba(255, 255, 255, 0.54)" }}
                    aria-label={`info about ${title}`}>
                    <InfoIcon />
                  </IconButton>
                }
              />
            </ImageListItem>
          ))}
        </Masonry>
      </Grid>
      </Box>
      </Grid>
      <Footer />
    </>
  );
};

export default ProfileUser;
