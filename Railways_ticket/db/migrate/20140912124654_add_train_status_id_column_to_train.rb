class AddTrainStatusIdColumnToTrain < ActiveRecord::Migration
  def change
    add_column :trains, :train_status_id, :integer
  end
end
