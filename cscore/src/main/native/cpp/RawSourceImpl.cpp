// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "RawSourceImpl.h"

#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>

#include <wpi/timestamp.h>

#include "Handle.h"
#include "Instance.h"
#include "Log.h"
#include "Notifier.h"
#include "cscore_raw.h"

using namespace cs;

RawSourceImpl::RawSourceImpl(std::string_view name, wpi::Logger& logger,
                             Notifier& notifier, Telemetry& telemetry,
                             const VideoMode& mode)
    : ConfigurableSourceImpl{name, logger, notifier, telemetry, mode} {}

RawSourceImpl::~RawSourceImpl() = default;

void RawSourceImpl::PutFrame(const WPI_RawFrame& image) {
  int type;
  switch (image.pixelFormat) {
    case VideoMode::kBGRA:
      // Special case BGRA to avoid a copy
      {
        type = CV_8UC4;
        cv::Mat finalImage{image.height, image.width, type, image.data,
                           static_cast<size_t>(image.stride)};
        std::unique_ptr<Image> dest =
            AllocImage(VideoMode::PixelFormat::kBGR,
                       image.width, image.height, image.size);
        cv::cvtColor(finalImage, dest->AsMat(), cv::COLOR_BGRA2BGR);
        SourceImpl::PutFrame(std::move(dest), wpi::Now());
      }
      return;
    case VideoMode::kYUYV:
    case VideoMode::kRGB565:
    case VideoMode::kY16:
    case VideoMode::kUYVY:
      type = CV_8UC2;
      break;
    case VideoMode::kBGR:
      type = CV_8UC3;
      break;
    case VideoMode::kGray:
    case VideoMode::kMJPEG:
    default:
      type = CV_8UC1;
      break;
  }
  cv::Mat finalImage{image.height, image.width, type, image.data,
                     static_cast<size_t>(image.stride)};
  std::unique_ptr<Image> dest =
      AllocImage(static_cast<VideoMode::PixelFormat>(image.pixelFormat),
                 image.width, image.height, image.size);
  finalImage.copyTo(dest->AsMat());

  SourceImpl::PutFrame(std::move(dest), wpi::Now());
}

namespace cs {
static constexpr unsigned SourceMask = CS_SOURCE_CV | CS_SOURCE_RAW;

CS_Source CreateRawSource(std::string_view name, bool isCv,
                          const VideoMode& mode, CS_Status* status) {
  auto& inst = Instance::GetInstance();
  return inst.CreateSource(
      isCv ? CS_SOURCE_CV : CS_SOURCE_RAW,
      std::make_shared<RawSourceImpl>(name, inst.logger, inst.notifier,
                                      inst.telemetry, mode));
}

void PutSourceFrame(CS_Source source, const WPI_RawFrame& image,
                    CS_Status* status) {
  auto data = Instance::GetInstance().GetSource(source);
  if (!data || (data->kind & SourceMask) == 0) {
    *status = CS_INVALID_HANDLE;
    return;
  }
  static_cast<RawSourceImpl&>(*data->source).PutFrame(image);
}

}  // namespace cs

extern "C" {
CS_Source CS_CreateRawSource(const char* name, CS_Bool isCv,
                             const CS_VideoMode* mode, CS_Status* status) {
  return cs::CreateRawSource(name, isCv,
                             static_cast<const cs::VideoMode&>(*mode), status);
}

void CS_PutRawSourceFrame(CS_Source source, const struct WPI_RawFrame* image,
                          CS_Status* status) {
  return cs::PutSourceFrame(source, *image, status);
}

}  // extern "C"
