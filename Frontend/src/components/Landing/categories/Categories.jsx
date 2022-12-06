import { Grid  } from "@mui/material";
import Buttons from "./components/Buttons";
import SearchBar from "./components/SearchBar";

const Categories = () => {

  
  return (
    <Grid sx={{ 
      flexGrow: 6,
      position: 'relative',
      top: 0,
      bottom: 0,
      right: 0,
      left: 0,
      paddingLeft:0,
      paddingRight:0,
      }}>
      <Grid container spacing={4}
      sx={{
          display:'flex',
          flexDirection:'column',
          alignContent:'center',
          justifyItems:'center'

        }}>
        <Grid item xs={4} md={8}>
          <Buttons/>
          <SearchBar/>
        </Grid>
      </Grid>
    </Grid>
  )
};

export default Categories;
