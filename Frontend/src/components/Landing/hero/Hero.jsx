import React from 'react'
import ButtonHero from './components/Button'
import theme from '../../../themeConfig'
import { Typography,Box , Grid} from '@mui/material'
// import Img from './components/static/erik-mclean-9XK7vgoGSgc-unsplash.jpeg'

const Hero = () =>
{
  return (
    <>
      <Grid item xl="auto" maxwidth='xl'
      sx={{
        position: 'relative',
        top: 0,
        bottom: 0,
        right: 0,
        left: 0,
        paddingLeft:0,
        paddingRight:0
      }}
      >
      <div maxwidth='xl' style={{
        backgroundImage:`url(${'https://source.unsplash.com/random'})`,
        backgroundRepeat:'no-repeat',
        backgroundPosition:'center',
        backgroundSize:'cover',
        minHeight:'100vh',
        position:'relative',
        mb:4
        }}>
        <Grid container>
        <Grid item md={6}>
        <Box sx={{
            position: 'relative',
            p: { xs: 3, md: 6 },
            pr: { md: 0 },
            marginTop:'150px'
          }}
          >
        <Typography theme={theme} color='secondary' variant='titleHero' lineHeight='1.2'>
        <p>Busque y descargue fotografía de autor.</p>
        <p>Venda y compre imágenes para su proyecto.</p>
        </Typography>
        <Typography  theme={theme} color='secondary' variant='subtitle1' lineHeight='1.2' >
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
        </Typography>
        <ButtonHero />
        </Box>
        </Grid>
      </Grid>
       {/* <img  src={wave}  width='100%' height='100%' alt="wave" position="absolute"/> */}
      </div>
      </Grid>
    </>
  )
}

export default Hero;


// style={{ backgroundImage: `url(${Img})`