
import { Typography, Grid } from '@mui/material'
import CardProfile from './card'



const Feactured = () =>
{
  return (
    <>
      <Grid >
      <Typography 
      color='#0000000'
      fontSize={'40px'}
      sx={{
        margin:'20px 20px 20px 20px',
        padding:'20px 20px 20px 20px'
        // textShadow:'0px 4px 4px',
      }}>
        Profesionales sugeridos
      </Typography>
      <Grid container
        direction="row"
        justifyContent="space-around"
        alignItems="center"
        spacing={2}
        sx={{
          marginButton:'20px',
          paddingBottom:'40px',
        }}
        >
        <CardProfile />
      </Grid>
      </Grid>
    </>
  )
}

export default Feactured;