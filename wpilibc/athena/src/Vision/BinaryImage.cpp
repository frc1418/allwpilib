/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2014-2016. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "Vision/BinaryImage.h"

#include <cstring>

#include "WPIErrors.h"

/**
 * Get then number of particles for the image.
 *
 * @return the number of particles found for the image.
 */
int32_t BinaryImage::GetNumberParticles() {
  int32_t numParticles = 0;
  int32_t success = imaqCountParticles(m_imaqImage, 1, &numParticles);
  wpi_setImaqErrorWithContext(success, "Error counting particles");
  return numParticles;
}

/**
 * Get a single particle analysis report.
 *
 * Get one (of possibly many) particle analysis reports for an image.
 *
 * @param particleNumber Which particle analysis report to return.
 * @return the selected particle analysis report
 */
ParticleAnalysisReport BinaryImage::GetParticleAnalysisReport(
    int32_t particleNumber) {
  ParticleAnalysisReport par;
  GetParticleAnalysisReport(particleNumber, &par);
  return par;
}

/**
 * Get a single particle analysis report.
 *
 * Get one (of possibly many) particle analysis reports for an image.
 * This version could be more efficient when copying many reports.
 *
 * @param particleNumber Which particle analysis report to return.
 * @param par            the selected particle analysis report
 */
void BinaryImage::GetParticleAnalysisReport(int32_t particleNumber,
                                            ParticleAnalysisReport* par) {
  int32_t success;
  int32_t numParticles = 0;

  success = imaqGetImageSize(m_imaqImage, &par->imageWidth, &par->imageHeight);
  wpi_setImaqErrorWithContext(success, "Error getting image size");
  if (StatusIsFatal()) return;

  success = imaqCountParticles(m_imaqImage, 1, &numParticles);
  wpi_setImaqErrorWithContext(success, "Error counting particles");
  if (StatusIsFatal()) return;

  if (particleNumber >= numParticles) {
    wpi_setWPIErrorWithContext(ParameterOutOfRange, "particleNumber");
    return;
  }

  par->particleIndex = particleNumber;
  // Don't bother measuring the rest of the particle if one fails
  bool good = ParticleMeasurement(particleNumber, IMAQ_MT_CENTER_OF_MASS_X,
                                  &par->center_mass_x);
  good = good && ParticleMeasurement(particleNumber, IMAQ_MT_CENTER_OF_MASS_Y,
                                     &par->center_mass_y);
  good = good &&
         ParticleMeasurement(particleNumber, IMAQ_MT_AREA, &par->particleArea);
  good = good && ParticleMeasurement(particleNumber, IMAQ_MT_BOUNDING_RECT_TOP,
                                     &par->boundingRect.top);
  good = good && ParticleMeasurement(particleNumber, IMAQ_MT_BOUNDING_RECT_LEFT,
                                     &par->boundingRect.left);
  good =
      good && ParticleMeasurement(particleNumber, IMAQ_MT_BOUNDING_RECT_HEIGHT,
                                  &par->boundingRect.height);
  good =
      good && ParticleMeasurement(particleNumber, IMAQ_MT_BOUNDING_RECT_WIDTH,
                                  &par->boundingRect.width);
  good = good && ParticleMeasurement(particleNumber, IMAQ_MT_AREA_BY_IMAGE_AREA,
                                     &par->particleToImagePercent);
  good = good && ParticleMeasurement(particleNumber,
                                     IMAQ_MT_AREA_BY_PARTICLE_AND_HOLES_AREA,
                                     &par->particleQuality);

  if (good) {
    /* normalized position (-1 to 1) */
    par->center_mass_x_normalized =
        NormalizeFromRange(par->center_mass_x, par->imageWidth);
    par->center_mass_y_normalized =
        NormalizeFromRange(par->center_mass_y, par->imageHeight);
  }
}

/**
 * Get an ordered vector of particles for the image.
 *
 * Create a vector of particle analysis reports sorted by size for an image.
 * The vector contains the actual report structures.
 *
 * @return a pointer to the vector of particle analysis reports. The caller
 *         must delete the vector when finished using it.
 */
std::vector<ParticleAnalysisReport>*
BinaryImage::GetOrderedParticleAnalysisReports() {
  auto particles = new std::vector<ParticleAnalysisReport>;
  int32_t particleCount = GetNumberParticles();
  for (int32_t particleIndex = 0; particleIndex < particleCount;
       particleIndex++) {
    particles->push_back(GetParticleAnalysisReport(particleIndex));
  }
  // TODO: This is pretty inefficient since each compare in the sort copies
  //   both reports being compared... do it manually instead... while we're
  //   at it, we should provide a version that allows a preallocated buffer of
  //   ParticleAnalysisReport structures
  sort(particles->begin(), particles->end(), CompareParticleSizes);
  return particles;
}

/**
 * Write a binary image to flash.
 *
 * Writes the binary image to flash on the cRIO for later inspection.
 *
 * @param fileName the name of the image file written to the flash.
 */
void BinaryImage::Write(const char* fileName) {
  RGBValue colorTable[256];
  std::memset(colorTable, 0, sizeof(colorTable));
  colorTable[0].R = 0;
  colorTable[1].R = 255;
  colorTable[0].G = colorTable[1].G = 0;
  colorTable[0].B = colorTable[1].B = 0;
  colorTable[0].alpha = colorTable[1].alpha = 0;
  imaqWriteFile(m_imaqImage, fileName, colorTable);
}

/**
 * Measure a single parameter for an image.
 *
 * Get the measurement for a single parameter about an image by calling the
 * imaqMeasureParticle function for the selected parameter.
 *
 * @param particleNumber which particle in the set of particles
 * @param whatToMeasure  the imaq MeasurementType (what to measure)
 * @param result         the value of the measurement
 * @return false on failure, true on success
 */
bool BinaryImage::ParticleMeasurement(int32_t particleNumber,
                                      MeasurementType whatToMeasure,
                                      int32_t* result) {
  double resultDouble;
  bool success =
      ParticleMeasurement(particleNumber, whatToMeasure, &resultDouble);
  *result = (int)resultDouble;
  return success;
}

/**
 * Measure a single parameter for an image.
 *
 * Get the measurement for a single parameter about an image by calling the
 * imaqMeasureParticle function for the selected parameter.
 *
 * @param particleNumber which particle in the set of particles
 * @param whatToMeasure  the imaq MeasurementType (what to measure)
 * @param result         the value of the measurement
 * @returns true on failure, false on success
 */
bool BinaryImage::ParticleMeasurement(int32_t particleNumber,
                                      MeasurementType whatToMeasure,
                                      double* result) {
  int32_t success;
  success = imaqMeasureParticle(m_imaqImage, particleNumber, 0, whatToMeasure,
                                result);
  wpi_setImaqErrorWithContext(success, "Error measuring particle");
  return !StatusIsFatal();
}

// Normalizes to [-1,1]
double BinaryImage::NormalizeFromRange(double position, int32_t range) {
  return (position * 2.0 / (double)range) - 1.0;
}

/**
 * The compare helper function for sort.
 *
 * This function compares two particle analysis reports as a helper for the sort
 * function.
 *
 * @param particle1 The first particle to compare
 * @param particle2 the second particle to compare
 * @returns true if particle1 is greater than particle2
 */
bool BinaryImage::CompareParticleSizes(ParticleAnalysisReport particle1,
                                       ParticleAnalysisReport particle2) {
  // we want descending sort order
  return particle1.particleToImagePercent > particle2.particleToImagePercent;
}

BinaryImage* BinaryImage::RemoveSmallObjects(bool connectivity8,
                                             int32_t erosions) {
  auto result = new BinaryImage();
  int32_t success =
      imaqSizeFilter(result->GetImaqImage(), m_imaqImage, connectivity8,
                     erosions, IMAQ_KEEP_LARGE, nullptr);
  wpi_setImaqErrorWithContext(success, "Error in RemoveSmallObjects");
  return result;
}

BinaryImage* BinaryImage::RemoveLargeObjects(bool connectivity8,
                                             int32_t erosions) {
  auto result = new BinaryImage();
  int32_t success =
      imaqSizeFilter(result->GetImaqImage(), m_imaqImage, connectivity8,
                     erosions, IMAQ_KEEP_SMALL, nullptr);
  wpi_setImaqErrorWithContext(success, "Error in RemoveLargeObjects");
  return result;
}

BinaryImage* BinaryImage::ConvexHull(bool connectivity8) {
  auto result = new BinaryImage();
  int32_t success =
      imaqConvexHull(result->GetImaqImage(), m_imaqImage, connectivity8);
  wpi_setImaqErrorWithContext(success, "Error in convex hull operation");
  return result;
}

BinaryImage* BinaryImage::ParticleFilter(ParticleFilterCriteria2* criteria,
                                         int32_t criteriaCount) {
  auto result = new BinaryImage();
  int32_t numParticles;
  ParticleFilterOptions2 filterOptions = {0, 0, 0, 1};
  int32_t success = imaqParticleFilter4(result->GetImaqImage(), m_imaqImage,
                                        criteria, criteriaCount, &filterOptions,
                                        nullptr, &numParticles);
  wpi_setImaqErrorWithContext(success, "Error in particle filter operation");
  return result;
}
