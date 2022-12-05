import { Container, Grid } from "@mui/material";
import logoBW from "./static/logo blanco viewfinder.png";

const Footer = () => {
  return (
    <Grid
      sx={{
        backgroundColor: "#000000",
        height: "100px",
      }}>
      <Grid
        container
        sx={{
          direction: "row",
          justifyContent: "flex-end",
          alignItems: "center",
          padding: "20px 20px 20px 40px",
        }}>
        <img xs={8} src={logoBW} width="220px" height="60px" />
      </Grid>
    </Grid>
  );
};

export default Footer;
